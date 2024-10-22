package to.pkgdo.list;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ToDoListGui extends JFrame implements ActionListener {
    
    private JPanel taskPanel, taskComponentPanel;
    
    
    public ToDoListGui(){
        
        super("To do List ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(CommonContants.GUI_SIZE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        
        addGuiComponent();           
    }
    
    private void addGuiComponent(){
    
        JLabel bannerLabel = new JLabel("To do List");
        bannerLabel.setBounds(
        (CommonContants.GUI_SIZE.width - bannerLabel.getPreferredSize().width)/3,
         15,
         CommonContants.BANNER_SIZE.width,
         CommonContants.BANNER_SIZE.height             
        );        
        bannerLabel.setFont(new Font("serif", Font.BOLD, 50));
                
        taskPanel = new JPanel();
        taskComponentPanel = new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel);
        
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBounds(8,70, CommonContants.TASKPANEL_SIZE.width, CommonContants.TASKPANEL_SIZE.height);
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setMaximumSize(CommonContants.TASKPANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);        
        
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);
                
        JButton addTaskButton = new JButton("Add task");
        addTaskButton.setBounds(-5,CommonContants.GUI_SIZE.height - 88, CommonContants.ADDTASK_BUTTON_SIZE.width, CommonContants.ADDTASK_BUTTON_SIZE.height );
        addTaskButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addTaskButton.addActionListener(this);
        
        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);        
    }
    
        @Override
        public void actionPerformed(ActionEvent e){
            String command = e.getActionCommand();
            if(command.equalsIgnoreCase("add Task")){
            
                TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
                taskComponentPanel.add(taskComponent);
                
                if(taskComponentPanel.getComponentCount() > 1){
                TaskComponent previousTask = (TaskComponent) taskComponentPanel.getComponent(
                        taskComponentPanel.getComponentCount() - 2);
                previousTask.getTaskField().setBackground(null);
                }
                 
                
                taskComponent.getTaskField().requestFocus();
                repaint();
                revalidate();                 
            } 
    }
    
}
