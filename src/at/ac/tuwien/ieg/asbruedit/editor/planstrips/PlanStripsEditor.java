package at.ac.tuwien.ieg.asbruedit.editor.planstrips;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStackEvent;
import org.eclipse.gef.commands.CommandStackEventListener;
import org.eclipse.gef.internal.InternalGEFPlugin;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.gef.ui.views.palette.PaletteViewerPage;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.openscada.ui.breadcrumbs.BreadcrumbViewer;
import org.openscada.ui.breadcrumbs.IBreadcrumbDropDownSite;

import at.ac.tuwien.ieg.asbruedit.Activator;
import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelElementFactory;
import at.ac.tuwien.ieg.asbruedit.editor.AsbruXMLEditorInput;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.commands.CopyAction;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.commands.PasteAction;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanActivationEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanLibraryEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanStripsEditPart;
import at.ac.tuwien.ieg.asbruedit.helpers.ColorTool;
import at.ac.tuwien.ieg.asbruedit.model.PlanType;
import at.ac.tuwien.ieg.asbruedit.model.asbru.CyclicalPlan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.ObjectFactory;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Plan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanActivation;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Subplans;
import at.ac.tuwien.ieg.asbruedit.model.asbru.UserPerformed;
import at.ac.tuwien.ieg.asbruedit.view.condition.ConditionInput;

public class PlanStripsEditor extends GraphicalEditor implements CommandStackEventListener, ISelectionChangedListener {
	public static final String ID = "at.ac.tuwien.ieg.asbruedit.asbrueditor";
	private PaletteViewerProvider provider;
	private FlyoutPaletteComposite splitter;
	private BreadcrumbViewer breadcrumb;
	private ISelectionListener breadcrumbSelectionListener;
	private CustomPalettePage page;
	private PaletteRoot paletteRoot;
	private Object model;
	private JAXBContext jaxbContext;

	public PlanStripsEditor() {
		DefaultEditDomain editDomain = new DefaultEditDomain(this);
		editDomain.setPaletteRoot(getPaletteRoot());
		setEditDomain(editDomain);
		getCommandStack().addCommandStackEventListener(this);
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		saveModel();
		getCommandStack().markSaveLocation();
		fireDirtyUpdate();
	}

	@Override
	public void dispose() {
		getCommandStack().removeCommandStackEventListener(this);
		getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(breadcrumbSelectionListener);
		PlanStripsEditorRegistry.instance().unregister(this);
		super.dispose();
	}

	protected void fireDirtyUpdate() {
		firePropertyChange(PROP_DIRTY);
	}

	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);
		setPartName(input.getName());
	}

	protected void saveModel() {
		try {
			getJaxbContext().createMarshaller().marshal(model, getEditorInput().getAsbruXmlFile());
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	protected void loadModel() {
		try {
			model = getJaxbContext().createUnmarshaller().unmarshal(getEditorInput().getAsbruXmlFile());
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	protected JAXBContext getJaxbContext() {
		if (jaxbContext == null) {
			try {
				jaxbContext = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName(), ObjectFactory.class.getClassLoader());
			} catch (JAXBException e) {
				throw new RuntimeException(e);
			}
			;
		}
		return jaxbContext;
	}

	@Override
	public Object getAdapter(Class type) {
		if(type.equals(ConditionsToggleAdapter.class)) {
			return new ConditionsToggleAdapter(this.getGraphicalViewer());
		}
		if(type.equals(PlanNameToggleAdapter.class)) {
			return new PlanNameToggleAdapter(this.getGraphicalViewer());
		}
		return super.getAdapter(type);
	}

	@Override
	public AsbruXMLEditorInput getEditorInput() {
		return (AsbruXMLEditorInput) super.getEditorInput();
	}

	@Override
	protected void initializeGraphicalViewer() {
		loadModel();
		getGraphicalViewer().setContents(model);
	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer().setEditPartFactory(new PlanStripsEditPartFactory());
	}

	protected PaletteRoot getPaletteRoot() {
		if (paletteRoot == null) {
			paletteRoot = new PaletteRoot();
			PaletteGroup manipGroup = new PaletteGroup("Selection Tools");
			paletteRoot.add(manipGroup);

			SelectionToolEntry selectionToolEntry = new SelectionToolEntry();
			manipGroup.add(selectionToolEntry);

			PaletteSeparator sep2 = new PaletteSeparator();
			paletteRoot.add(sep2);
			PaletteGroup instGroup = new PaletteGroup("Creation Tools");
			instGroup.setLabel("Creation Tools");
			paletteRoot.add(instGroup);
			instGroup.add(new CombinedTemplateCreationEntry("Parallel", "Create a parallel sub-plan", new AsbruModelElementFactory(Subplans.class)
					.setPlanType(PlanType.parallel), AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/parallel_large.png"),
					AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/parallel_large.png")));
			instGroup.add(new CombinedTemplateCreationEntry("Any-order", "Create an any-order sub-plan", new AsbruModelElementFactory(Subplans.class)
					.setPlanType(PlanType.anyorder), AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/any-order_large.png"),
					AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/any-order_large.png")));
			instGroup.add(new CombinedTemplateCreationEntry("Unordered", "Create an unordered sub-plan", new AsbruModelElementFactory(Subplans.class)
					.setPlanType(PlanType.unordered), AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/unordered_large.png"),
					AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/unordered_large.png")));
			instGroup.add(new CombinedTemplateCreationEntry("Sequential", "Create a sequential sub-plan", new AsbruModelElementFactory(Subplans.class)
					.setPlanType(PlanType.sequential), AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/sequential_large.png"),
					AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/sequential_large.png")));
			instGroup.add(new CombinedTemplateCreationEntry("Cyclical", "Create a cyclical plan", new AsbruModelElementFactory(CyclicalPlan.class),
					AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/cyclical_large.png"), AbstractUIPlugin.imageDescriptorFromPlugin(
							Activator.PLUGIN_ID, "icons/cyclical_large.png")));
			instGroup.add(new CombinedTemplateCreationEntry("User-performed", "Create a user-performed", new AsbruModelElementFactory(UserPerformed.class),
					AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/user-performed_large.png"), AbstractUIPlugin
							.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/user-performed_large.png")));
			instGroup.add(new CombinedTemplateCreationEntry("Skeletal Plan", "Create a skeletal plan", new AsbruModelElementFactory(Plan.class),
					AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/skeletal-plan_large.png"), AbstractUIPlugin.imageDescriptorFromPlugin(
							Activator.PLUGIN_ID, "icons/skeletal-plan_large.png")));

			paletteRoot.setDefaultEntry(selectionToolEntry);
		}

		return paletteRoot;
	}
	
	@Override
	public void createActions() {
		super.createActions();
		ActionRegistry registry = getActionRegistry();
		IAction action = new CopyAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		action = new PasteAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite contentPane = makeContentPane(parent);

		makeBreadcrumb(contentPane);
		makeEditor(contentPane);
		
		// register a selection listener to react to edit part selections from within the editor
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(breadcrumbSelectionListener = new PlanStripsSelectionListener(PlanStripsEditPart.class) {
			@Override
			protected void selectionChanged(IWorkbenchPart part, ISelection selection, LinkedList<Object> supportedSelection, LinkedList<Object> unsupportedSelection) {		
				// only 1 element should be selected, else we can't tell which we should display the breadcrumb
				if(supportedSelection.size() == 1) {
					breadcrumb.setInput(supportedSelection.getFirst());
				}
			}
		});
		

		PlanStripsEditorRegistry.instance().register(this);
	}
	
	/**
	 * Create the content pane that the editors elements are located on
	 * @param parent Parent {@link Composite} passed by the framework
	 * @return Newly created content pane
	 */
	protected Composite makeContentPane(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		GridLayout contentLayout = new GridLayout(1, true);
		contentLayout.marginWidth = 0;
		contentLayout.marginHeight = 0;
		contentLayout.verticalSpacing = 0;
		content.setLayout(contentLayout);
		return content;
	}

	/**
	 * Creates a breadcrumb viewer on the provided parent composite.
	 * @param parent Composite to create the breadcrumb viewer on.
	 */
	protected void makeBreadcrumb(Composite parent) {
		breadcrumb = new BreadcrumbViewer(parent, SWT.None) {
			@Override
			protected Control createDropDown(Composite parent, IBreadcrumbDropDownSite site, TreePath path) {
				Label l = new Label(parent, SWT.None);
				return l;
			}
		};
		breadcrumb.setContentProvider(new BreadcrumbContentProvider());
		breadcrumb.setLabelProvider(new BreadcrumbLabelProvider());
		// react to selection events on the breadcrumb
		breadcrumb.addSelectionChangedListener(this);
		// this is a workaround because the widget does never show if no input is set right at the start (why unknown)
		breadcrumb.setInput(BreadcrumbContentProvider.NO_INPUT);
	}
	
	/**
	 * Create the editor with a flyout palette on the parent composite.
	 * @param parent Composite to create the editor on.
	 */
	protected void makeEditor(Composite parent) {
		splitter = new FlyoutPaletteComposite(parent, SWT.NONE, getSite().getPage(), getPaletteViewerProvider(), getPalettePreferences());
		super.createPartControl(splitter);
		splitter.setGraphicalControl(getGraphicalControl());
		if (page != null) {
			splitter.setExternalViewer(page.getPaletteViewer());
			page = null;
		}

		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		splitter.setLayoutData(gridData);
	}

	protected Control getGraphicalControl() {
		return getGraphicalViewer().getControl();
	}

	protected FlyoutPreferences getPalettePreferences() {
		return FlyoutPaletteComposite.createFlyoutPreferences(InternalGEFPlugin.getDefault().getPluginPreferences());
	}

	protected final PaletteViewerProvider getPaletteViewerProvider() {
		if (provider == null)
			provider = new PaletteViewerProvider(getEditDomain());
		return provider;
	}

	protected class CustomPalettePage extends PaletteViewerPage {
		/**
		 * Constructor
		 * 
		 * @param provider
		 *            the provider used to create a PaletteViewer
		 */
		public CustomPalettePage(PaletteViewerProvider provider) {
			super(provider);
		}

		public void createControl(Composite parent) {
			super.createControl(parent);
			if (splitter != null)
				splitter.setExternalViewer(viewer);
		}

		public void dispose() {
			if (splitter != null)
				splitter.setExternalViewer(null);
			super.dispose();
		}

		/**
		 * @return the PaletteViewer created and displayed by this page
		 */
		public PaletteViewer getPaletteViewer() {
			return viewer;
		}
	}

	@Override
	public void stackChanged(CommandStackEvent event) {
		fireDirtyUpdate();
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		// if the selection comes from the breadcrumb trail
		if(event.getSelectionProvider().equals(breadcrumb)) {
			// get the selected element (only 1 allowed)
			Object selectedElement = ((TreeSelection)event.getSelection()).getFirstElement();
			// if the selection refers to an edit part - it always should
			if(selectedElement != null && PlanStripsEditPart.class.isAssignableFrom(selectedElement.getClass())) {
				// select the edit part chosen in the breadcrumb trail
				getGraphicalViewer().select((PlanStripsEditPart)selectedElement);
			}
		}
	}
}
