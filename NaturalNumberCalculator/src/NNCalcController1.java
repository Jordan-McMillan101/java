import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 * 
 * @author Jordan L. McMillan
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     * 
     * @ensures <pre>
     * {@code [this.view has been updated to be consistent with this.model]}
     * </pre>
     */
    private void updateViewToMatchModel() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        this.view.updateTopDisplay(top);
        this.view.updateBottomDisplay(bottom);

        if (bottom.compareTo(top) > 0) {
            this.view.updateSubtractAllowed(false);
        } else {
            this.view.updateSubtractAllowed(true);
        }
        if (bottom.isZero()) {
            this.view.updateDivideAllowed(false);
        } else {
            this.view.updateDivideAllowed(true);
        }
        if (bottom.compareTo(TWO) >= 0) {
            this.view.updateRootAllowed(true);
        } else {
            this.view.updateRootAllowed(false);
        }
        // check to make sure the bottom number isn't greater than MAX_INTEGER
        if (bottom.compareTo(INT_LIMIT) > 0) {
            this.view.updateRootAllowed(false);
            this.view.updatePowerAllowed(false);
        } else {
            this.view.updateRootAllowed(true);
            this.view.updatePowerAllowed(true);
        }

    }

    /**
     * Constructor.
     * 
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        this.updateViewToMatchModel();
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        this.updateViewToMatchModel();
    }

    @Override
    public void processEnterEvent() {

        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        top.copyFrom(bottom);

        this.updateViewToMatchModel();

    }

    @Override
    public void processAddEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        top.add(bottom);
        bottom.transferFrom(top);

        this.updateViewToMatchModel();

    }

    @Override
    public void processSubtractEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        top.subtract(bottom);
        bottom.transferFrom(top);

        this.updateViewToMatchModel();
    }

    @Override
    public void processMultiplyEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        top.multiply(bottom);
        bottom.transferFrom(top);

        this.updateViewToMatchModel();
    }

    @Override
    public void processDivideEvent() {

        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        top.divide(bottom);
        bottom.transferFrom(top);

        this.updateViewToMatchModel();

    }

    @Override
    public void processPowerEvent() {
        NaturalNumber bottom = this.model.bottom();
        NaturalNumber top = this.model.top();

        int power = bottom.toInt();
        top.power(power);

        bottom.transferFrom(top);
        this.updateViewToMatchModel();
    }

    @Override
    public void processRootEvent() {
        NaturalNumber bottom = this.model.bottom();
        NaturalNumber top = this.model.top();

        int root = bottom.toInt();
        top.root(root);

        bottom.transferFrom(top);
        this.updateViewToMatchModel();

    }

    @Override
    public void processAddNewDigitEvent(int digit) {
        NaturalNumber bottom = this.model.bottom();

        bottom.multiplyBy10(digit);

        this.updateViewToMatchModel();
    }

}
