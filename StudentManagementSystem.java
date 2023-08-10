import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.table.*;

public class StudentManagementSystem extends JFrame {
    private JTextField nameTextField,fNameTextField, rollNumberTextField, mailTextField, contactTextField, gradeTextField, attendanceTextField;
    private JButton addButton, editButton, deleteButton;
    private JTable table;
    private DefaultTableModel tableModel;
    private ArrayList<Student> students;

    public StudentManagementSystem() {
        // Initialize the frame
        setTitle("Student Management System");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Father Name");
        tableModel.addColumn("Roll Number");
        tableModel.addColumn("Email Id");
        tableModel.addColumn("Contact");
        tableModel.addColumn("Attendance");
        tableModel.addColumn("Grade");
        table = new JTable(tableModel);
        table.setBackground(Color.pink);

        // Create the text fields
        nameTextField = new JTextField(15);
        fNameTextField = new JTextField(15);
        rollNumberTextField = new JTextField(15);
        mailTextField = new JTextField(15);
        contactTextField = new JTextField(15);
        attendanceTextField = new JTextField(15);
        gradeTextField = new JTextField(15);


        // Create the buttons
        addButton = new JButton("Add");
        addButton.setBackground(Color.green);
        editButton = new JButton("Edit");
        editButton.setBackground(Color.yellow);
        deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.red);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Add components to the frame
        setLayout(new BorderLayout(10,10));
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(createFormPanel(), BorderLayout.NORTH);

        // Initialize the student list
        students = new ArrayList<>();
        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(7, 2,10,10));
        formPanel.setFont(new Font("SANS_SERIF",  Font.BOLD, 25));
        formPanel.add(new JLabel("Name :"));
        formPanel.add(nameTextField);
        formPanel.add(new JLabel("Father's Name :"));
        formPanel.add(fNameTextField);
        formPanel.add(new JLabel("Roll Number:"));
        formPanel.add(rollNumberTextField);
        formPanel.add(new JLabel("Email-Id :"));
        formPanel.add(mailTextField);
        formPanel.add(new JLabel("Contact :"));
        formPanel.add(contactTextField);
        formPanel.add(new JLabel("Attendencs"));
        formPanel.add(attendanceTextField);
        formPanel.add(new JLabel("Grade"));
        formPanel.add(gradeTextField);

        return formPanel;
    }

    private void addStudent() {
        String name = nameTextField.getText();
        String fName = fNameTextField.getText();
        String rollNumber = rollNumberTextField.getText();
        String email = mailTextField.getText();
        String contact = contactTextField.getText();
        String attendance = attendanceTextField.getText();
        String grade = gradeTextField.getText();

        if (!name.isEmpty() && !fName.isEmpty() && !rollNumber.isEmpty() && !email.isEmpty() && !contact.isEmpty() && !attendance.isEmpty() && !grade.isEmpty()) {
            Student student = new Student(name,fName, rollNumber, email, contact, attendance, grade);
            students.add(student);
            tableModel.addRow(new Object[]{name,fName, rollNumber, email, contact, attendance, grade});
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter name and roll number.");
        }
    }

    private void editStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String newName = nameTextField.getText();
            String newFName = nameTextField.getText();
            String newRollNumber = rollNumberTextField.getText();
            String newMail = nameTextField.getText();
            String newContact = nameTextField.getText();
            String newAttendance = nameTextField.getText();
            String newGrade = nameTextField.getText();
            if (!newName.isEmpty() && !newFName.isEmpty() && !newRollNumber.isEmpty() && !newContact.isEmpty() && !newMail.isEmpty() && !newAttendance.isEmpty() && !newGrade.isEmpty()) {
                Student student = students.get(selectedRow);
                student.setName(newName);
                student.setFName(newFName);
                student.setRollNumber(newRollNumber);
                student.setEmail(newMail);
                student.setContact(newContact);
                student.setAttendance(newAttendance);
                student.setGrade(newGrade);
                tableModel.setValueAt(newName, selectedRow, 0);
                tableModel.setValueAt(newFName, selectedRow, 1);
                tableModel.setValueAt(newRollNumber, selectedRow, 2);
                tableModel.setValueAt(newMail, selectedRow, 3);
                tableModel.setValueAt(newContact, selectedRow, 4);
                tableModel.setValueAt(newAttendance, selectedRow, 5);
                tableModel.setValueAt(newGrade, selectedRow, 6);
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter name and roll number.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
        }
    }

    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            students.remove(selectedRow);
            tableModel.removeRow(selectedRow);
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }
    }

    private void clearForm() {
        nameTextField.setText("");
        rollNumberTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StudentManagementSystem sms = new StudentManagementSystem();
                sms.setVisible(true);
            }
        });
    }
}

class Student {
    private String name;
    private String fName;
    private String rollNumber;
    private String mail;
    private String contact;
    private String attendance;
    private String grade;

    public Student(String name,String fName, String rollNumber, String mail, String contact, String attendance, String grade) {
        this.name = name;
        this.fName = fName;
        this.rollNumber = rollNumber;
        this.mail = mail;
        this.contact = contact;
        this.attendance = attendance;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFName(){return fName;}
    public void setFName(String fName){this.fName = fName;}

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getEmail(){return mail;}
    public void setEmail(String mail) {this.contact = mail;}

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAttendance() {
        return attendance;
    }
    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
