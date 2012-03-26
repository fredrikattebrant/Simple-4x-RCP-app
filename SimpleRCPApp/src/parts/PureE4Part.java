package parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Pure e4 part that listens and displays the current selection
 * 
 */
@SuppressWarnings("restriction")
public class PureE4Part {

	private Label label;
	private boolean disabled = true;
	private Logger logger;
	
	@Inject
	public PureE4Part(Logger logger) {
		//label = new Label(parent, SWT.NONE);
		this.logger = logger;
		logger.info("Clean 4.x part created");
	}
	
	@PostConstruct
	public void createUI(Composite parent) {
		label = new Label(parent, SWT.NONE);
		disabled = false;
		logger.info("Clean 4.x part UI created and enabled");
	}

	@PreDestroy
	public void destroy() {
		disabled = true;
		logger.info("Clean 4.x part disabled and destroyed");
	}
	
	@Inject
	public void setSelection(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) Object selection) {
		if (disabled) {
			return;
		}
		if (selection == null) {
			label.setText("Selection changed to 'null'");
		} else {
			label.setText("Selection changed: " + selection.toString());
		}
	}
}