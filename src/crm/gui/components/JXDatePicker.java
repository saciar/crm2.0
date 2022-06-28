package crm.gui.components;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.Date;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.border.*;
import org.jdesktop.swing.calendar.*;

import crm.gui.Main;

/**
 * A component that combines a button, an editable field and a JXMonthView
 * component.  The user can select a date from the calendar component, which
 * appears when the button is pressed.  The selection from the calendar
 * component will be displayed in editable field.  Values may also be modified
 * manually by entering a date into the editable field using one of the
 * supported date formats.
 *
 * @author Joshua Outwater
 */
public class JXDatePicker extends JComponent {
    /** The editable date field that displays the date */
    protected JFormattedTextField _dateField;
    /**
     * Popup that displays the month view with controls for
     * traversing/selecting dates.
     */
    protected JXDatePickerPopup _popup;
    private JButton _popupButton;
    private int _popupButtonWidth = 20;
    private JXMonthView _monthView;
    private Handler _handler;
    private String _actionCommand = "selectionChanged";

    /**
     * Create a new date picker using the current date as the initial
     * selection.
     */
    public JXDatePicker() {
        this(System.currentTimeMillis());
    }

    /**
     * Create a new date picker using the specified time as the initial
     * seleciton.
     *
     * @param millis initial time in milliseconds
     */
    public JXDatePicker(long millis) {
        _dateField = new JFormattedTextField(
            new JXDatePickerFormatterFactory());
        _dateField.setName("dateField");
        _dateField.setBorder(null);

        _handler = new Handler();
        _popupButton = new JButton();
        _popupButton.setName("popupButton");
        _popupButton.setRolloverEnabled(false);
        _popupButton.addMouseListener(_handler);
        _popupButton.addMouseMotionListener(_handler);

        KeyStroke enterKey =
            KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);

        InputMap inputMap = _dateField.getInputMap(JComponent.WHEN_FOCUSED);
        inputMap.put(enterKey, "COMMIT_EDIT");

        ActionMap actionMap = _dateField.getActionMap();
        actionMap.put("COMMIT_EDIT", new CommitEditAction());

        KeyStroke spaceKey =
            KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false);

        inputMap = _popupButton.getInputMap(JComponent.WHEN_FOCUSED);
        inputMap.put(spaceKey, "TOGGLE_POPUP");

        actionMap = _popupButton.getActionMap();
        actionMap.put("TOGGLE_POPUP", new TogglePopupAction());

        add(_dateField);
        add(_popupButton);

        updateUI();        

        _dateField.setValue(new Date(millis));
    }

    /**
     * Resets the UI property to a value from the current look and feel.
     */
    public void updateUI() {
        int cols = UIManager.getInt("JXDatePicker.numColumns");
        if (cols == -1) {
            cols = 10;
        }
        _dateField.setColumns(cols);

        String str = UIManager.getString("JXDatePicker.arrowDown.tooltip");
        if (str == null) {
            str = "Mostrar Calendario";
        }
        _popupButton.setToolTipText(str);

        Icon icon = UIManager.getIcon("JXDatePicker.arrowDown.image");
        if (icon == null) {
            icon = /* ImageIcon(getClass().getResource(
                    "/toolbarButtonGraphics/navigation/Down24.gif"));  */
            	new javax.swing.JLabel() {
                    public javax.swing.Icon getIcon() {
//                      try {
                        return new javax.swing.ImageIcon(
                        		Main.class.getResource("imagenes/calendar.png")//new java.net.URL("http://192.168.1.11:8888/app_files/arrow_right.png")
                        );
                  //  } catch (java.net.MalformedURLException e) {
                   // }
                    //return null;
                    }
                }.getIcon();
        }
        _popupButton.setIcon(icon);

        Border border = UIManager.getBorder("JXDatePicker.border");
        if (border == null) {
            border = BorderFactory.createCompoundBorder(
                    LineBorder.createGrayLineBorder(),
                    BorderFactory.createEmptyBorder(3, 3, 3, 3));
        }
        _dateField.setBorder(border);
    }

    /**
     * Set the currently selected date.
     *
     * @param date date
     */
    public void setDate(Date date) {
        _dateField.setValue(date);
    }

    /**
     * Set the currently selected date.
     *
     * @param millis milliseconds
     */
    public void setDateInMillis(long millis) {
        _dateField.setValue(new Date(millis));
    }

    /**
     * Returns the currently selected date.
     *
     * @return Date
     */
    public Date getDate() {
        return (Date)_dateField.getValue();
    }

    /**
     * Returns the currently selected date in milliseconds.
     *
     * @return the date in milliseconds
     */
    public long getDateInMillis() {
        return ((Date)_dateField.getValue()).getTime();
    }

    /**
     * Returns the formatted text field used to edit the date selection.
     *
     * @return the formatted text field
     */
    public JFormattedTextField getEditor() {
        return _dateField;
    }

    /**
     * Return the AbstractFormatterFactory for this instance of the
     * JXDatePicker.
     *
     * @return the AbstractFormatterFactory
     */
    public AbstractFormatterFactory getDateFormatterFactory() {
        return _dateField.getFormatterFactory();
    }

    /**
     * Set the AbstractFormatterFactory to be used by this instance of the
     * JXDatePicker.
     *
     * @param dateFormatterFactory the AbstractFormatterFactory
     */
    public void setDateFormatterFactory(
            AbstractFormatterFactory dateFormatterFactory) {
        _dateField.setFormatterFactory(dateFormatterFactory);
    }

    /**
     * Returns true if the current value being edited is valid.
     *
     * @return true if the current value being edited is valid.
     */
    public boolean isEditValid() {
        return _dateField.isEditValid();
    }

    /**
     * Forces the current value to be taken from the AbstractFormatter and
     * set as the current value. This has no effect if there is no current
     * AbstractFormatter installed.
     */
    public void commitEdit() throws ParseException {
        _dateField.commitEdit();
    }

    /**
     * Enables or disables the date picker and all its subcomponents.
     *
     * @param value true to enable, false to disable
     */
    public void setEnabled(boolean value) {
        if (isEnabled() == value) {
            return;
        }

        super.setEnabled(value);
        _dateField.setEditable(value);
        _popupButton.setVisible(value);
        //_dateField.setEnabled(value);
        //_popupButton.setEnabled(value);
    }

    /**
     * Returns the string currently used to identiy fired ActionEvents.
     *
     * @return String The string used for identifying ActionEvents.
     */
    public String getActionCommand() {
        return _actionCommand;
    }

    /**
     * Sets the string used to identify fired ActionEvents.
     *
     * @param actionCommand The string used for identifying ActionEvents.
     */
    public void setActionCommand(String actionCommand) {
        _actionCommand = actionCommand;
    }

    /**
     * Adds an ActionListener.
     * <p>
     * The ActionListener will receive an ActionEvent when a selection has
     * been made.
     *
     * @param l The ActionListener that is to be notified
     */
    public void addActionListener(ActionListener l) {
        listenerList.add(ActionListener.class, l);
    }

    /**
     * Removes an ActionListener.
     *
     * @param l The action listener to remove.
     */
    public void removeActionListener(ActionListener l) {
        listenerList.remove(ActionListener.class, l);
    }

    /**
     * Fires an ActionEvent to all listeners.
     */
    protected void fireActionPerformed() {
        Object[] listeners = listenerList.getListenerList();
        ActionEvent e = null;
        for (int i = listeners.length - 2; i >= 0; i -=2) {
            if (listeners[i] == ActionListener.class) {
                if (e == null) {
                    e = new ActionEvent(JXDatePicker.this,
                            ActionEvent.ACTION_PERFORMED,
                            _actionCommand);
                }
                ((ActionListener)listeners[i + 1]).actionPerformed(e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void doLayout() {
        int width = getWidth();
        int height = getHeight();

        Insets insets = getInsets();
        _dateField.setBounds(insets.left,
                insets.bottom,
                width - _popupButtonWidth,
                height);
        _popupButton.setBounds(width - _popupButtonWidth + insets.left,
                insets.bottom,
                _popupButtonWidth,
                height);
    }

    /**
     * {@inheritDoc}
     */
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    /**
     * {@inheritDoc}
     */
    public Dimension getPreferredSize() {
        Dimension dim = _dateField.getPreferredSize();
        dim.width += _popupButton.getPreferredSize().width;
        Insets insets = getInsets();
        dim.width += insets.left + insets.right;
        dim.height += insets.top + insets.bottom;        
        return dim;
    }

    /**
     * Action used to commit the current value in the JFormattedTextField.
     * This action is used by the keyboard bindings.
     */
    private class TogglePopupAction extends AbstractAction {
        public TogglePopupAction() {
            super("TogglePopup");
        }

        public void actionPerformed(ActionEvent ev) {
            _handler.toggleShowPopup();
        }
    }

    /**
     * Action used to commit the current value in the JFormattedTextField.
     * This action is used by the keyboard bindings.
     */
    private class CommitEditAction extends AbstractAction {
        public CommitEditAction() {
            super("CommitEditPopup");
        }

        public void actionPerformed(ActionEvent ev) {
            try {
                // Commit the current value.
                _dateField.commitEdit();

                // Reformat the value according to the formatter.
                _dateField.setValue(_dateField.getValue());
                fireActionPerformed();
            } catch (java.text.ParseException ex) {
            }
        }
    }

    private class Handler implements MouseListener, MouseMotionListener {
        private boolean _forwardReleaseEvent = false;

        public void mouseClicked(MouseEvent ev) {
        }

        public void mousePressed(MouseEvent ev) {
            if (!isEnabled()) {
                return;
            }

            if (_dateField.isEditValid()) {
                try {
                    _dateField.commitEdit();
                } catch (java.text.ParseException ex) {
                }
            }
            toggleShowPopup();
        }

        public void mouseReleased(MouseEvent ev) {
            // Retarget mouse event to the month view.
            if (_forwardReleaseEvent) {
                ev = SwingUtilities.convertMouseEvent(_popupButton, ev,
                        _monthView);
                _monthView.dispatchEvent(ev);
                _forwardReleaseEvent = false;
            }
        }

        public void mouseEntered(MouseEvent ev) {
        }

        public void mouseExited(MouseEvent ev) {
        }

        public void mouseDragged(MouseEvent ev) {
            _forwardReleaseEvent = true;

            if (!_popup.isShowing()) {
                return;
            }

            // Retarget mouse event to the month view.
            ev = SwingUtilities.convertMouseEvent(_popupButton, ev, _monthView);
            _monthView.dispatchEvent(ev);
        }

        public void mouseMoved(MouseEvent ev) {
        }

        public void toggleShowPopup() {
            if (_popup == null) {
                _popup = new JXDatePickerPopup();
                //_popup.setPreferredSize(new Dimension(300,500));
            }
            if (!_popup.isVisible()) {
                if (_dateField.getValue() == null) {
                    _dateField.setValue(new Date(System.currentTimeMillis()));
                }
                DateSpan span =
                        new DateSpan((java.util.Date)_dateField.getValue(),
                                (java.util.Date)_dateField.getValue());
                _monthView.setSelectedDateSpan(span);
                _monthView.ensureDateVisible(
                        ((Date)_dateField.getValue()).getTime());
                Point loc = _dateField.getLocationOnScreen();
                
                _popup.show(JXDatePicker.this,
                        0, JXDatePicker.this.getHeight());
            } else {
                _popup.setVisible(false);
            }
        }
    }

    /**
     * Popup component that shows a JXMonthView component along with controlling
     * buttons to allow traversal of the months.  Upon selection of a date the
     * popup will automatically hide itself and enter the selection into the
     * editable field of the JXDatePicker.
     */
    private class JXDatePickerPopup extends JPopupMenu
            implements ActionListener {
        private JButton _nextButton;
        private JButton _previousButton;
        private JButton _todayButton;

        public JXDatePickerPopup() {
            _monthView = new JXMonthView();
            
            String[] dias = new String[7];
            dias[0]="D";
            dias[1]="L";
            dias[2]="M";
            dias[3]="M";
            dias[4]="J";
            dias[5]="V";
            dias[6]="S";            
            _monthView.setDaysOfTheWeek(dias);
            
            _monthView.setActionCommand("MONTH_VIEW");
            _monthView.addActionListener(this);

            JPanel panel = new JPanel(new FlowLayout());
            Icon icon = UIManager.getIcon("JXMonthView.monthUp.image");
            if (icon == null) {
            	icon = //new ImageIcon(getClass().getResource(
                    //"/toolbarButtonGraphics/navigation/Down24.gif"));
            	new javax.swing.JLabel() {
                public javax.swing.Icon getIcon() {
                  //  try {
                        return new javax.swing.ImageIcon(
                        		Main.class.getResource("imagenes/arrow_left.png")//new java.net.URL("http://192.168.1.11:8888/app_files/arrow_right.png")
                        );
                  //  } catch (java.net.MalformedURLException e) {
                   // }
                    //return null;
                }
            }.getIcon();
            }
            _previousButton = new JButton("Ant.",icon);
            _previousButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            _previousButton.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
            _previousButton.setActionCommand("PREVIOUS_MONTH");
            _previousButton.addActionListener(this);

            icon = UIManager.getIcon("JXMonthView.monthDown.image");
            if (icon == null) {
                icon = //new ImageIcon(getClass().getResource(
                        //"/toolbarButtonGraphics/navigation/Down24.gif"));
                	new javax.swing.JLabel() {
                    public javax.swing.Icon getIcon() {
                      //  try {
                            return new javax.swing.ImageIcon(
                            		Main.class.getResource("imagenes/arrow_right.png")//new java.net.URL("http://192.168.1.11:8888/app_files/arrow_right.png")
                            );
                      //  } catch (java.net.MalformedURLException e) {
                       // }
                        //return null;
                    }
                }.getIcon();
            }
            _nextButton = new JButton("Próx.",icon);
            _nextButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            _nextButton.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
            _nextButton.setActionCommand("NEXT_MONTH");
            _nextButton.addActionListener(this);

            icon = UIManager.getIcon("JXMonthView.monthCurrent.image");
            if (icon == null) {
            	icon = //new ImageIcon(getClass().getResource(
                    //"/toolbarButtonGraphics/navigation/Down24.gif"));
            	new javax.swing.JLabel() {
                public javax.swing.Icon getIcon() {
                  //  try {
                        return new javax.swing.ImageIcon(
                        		Main.class.getResource("imagenes/calendar_view_day.png")//new java.net.URL("http://192.168.1.11:8888/app_files/arrow_right.png")
                        );
                  //  } catch (java.net.MalformedURLException e) {
                   // }
                    //return null;
                }
            }.getIcon();              		
            }
            _todayButton = new JButton(null,icon);
            _todayButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            _todayButton.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
            _todayButton.setActionCommand("TODAY");
            _todayButton.addActionListener(this);

            setLayout(new BorderLayout());
            add(_monthView, BorderLayout.CENTER);

            panel.add(_previousButton);
            panel.add(_todayButton);
            panel.add(_nextButton);
            add(panel, BorderLayout.NORTH);
        }

        public void actionPerformed(ActionEvent ev) {
            String command = ev.getActionCommand();
            if ("MONTH_VIEW" == command) {
                DateSpan span = _monthView.getSelectedDateSpan();
                _dateField.setValue(span.getStartAsDate());
                _popup.setVisible(false);
                fireActionPerformed();
            } else if ("PREVIOUS_MONTH" == command) {
                _monthView.setFirstDisplayedDate(DateUtils.getPreviousMonth(
                              _monthView.getFirstDisplayedDate()));
            } else if ("NEXT_MONTH" == command) {
                _monthView.setFirstDisplayedDate(DateUtils.getNextMonth(
                                   _monthView.getFirstDisplayedDate()));
            } else if ("TODAY" == command) {
                DateSpan span = new DateSpan(System.currentTimeMillis(),
                    System.currentTimeMillis());
                _monthView.ensureDateVisible(span.getStart());
            }
        }
    }
}