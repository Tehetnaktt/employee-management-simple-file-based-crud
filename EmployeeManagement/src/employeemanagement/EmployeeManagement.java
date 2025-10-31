/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Tehetena
 */
public class EmployeeManagement extends Application {
    
    private BorderPane root;
    private StackPane currentPane;
    private StackPane registrationPane, viewPane, updatePane, deletePane;

    @Override
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {
        // Create the root layout
        root = new BorderPane();
        root.getStyleClass().add("root");

        // Create the menu bar at the top
        HBox menuBar = new HBox();
        menuBar.getStyleClass().add("menu-bar");
        Button registrationButton = new Button("Registration");
        Button viewButton = new Button("View");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        menuBar.getChildren().addAll(registrationButton, viewButton, updateButton, deleteButton);
        root.setTop(menuBar);

        // Create the panes
        registrationPane = createRegistrationPane();
        viewPane = createViewPane();
        updatePane = createUpdatePane();
        deletePane = createDeletePane();
        // Set the initial pane (registration pane) in the center
        currentPane = registrationPane;
        root.setCenter(currentPane);
        // Add action listeners to the menu buttons
        registrationButton.setOnAction(e -> showPane(registrationPane));
        viewButton.setOnAction(e -> showPane(viewPane));
        updateButton.setOnAction(e -> showPane(updatePane));
        deleteButton.setOnAction(e -> showPane(deletePane));
        // Create the scene and show the stage
        Scene scene = new Scene(root, 900, 800);
        scene.getStylesheets().add("s1.css");
        primaryStage.setTitle("Employee Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    public StackPane createRegistrationPane() {
       
      Label empfirstname= new Label("First Name:");
        Label empmiddlename= new Label("Middle Name");
        Label emplastname= new Label("Last Name");
       Label empemail= new Label("Email");
       Label empage= new Label("Age");
        Label empgender= new Label("Gender");
         Label empcontact= new Label("Contact");
          Label emphireDate= new Label("HireDate");
           Label empcountry= new Label("Country");
           Label empcity= new Label("City");
            Label empsubcity= new Label("Subcity");
             Label empdepartment= new Label("Department");
              Label empposition= new Label("Position");
              
              TextField Firstname = new TextField();
                TextField Middlename = new TextField();
                   TextField Lastname = new TextField();
                      TextField Email = new TextField();
                        TextField Age = new TextField();
                        RadioButton rbtM=new RadioButton("Male");   
                          RadioButton rbtF=new RadioButton("Female");
                          ToggleGroup sex=new ToggleGroup();
                          rbtM.setToggleGroup(sex);
                          rbtF.setToggleGroup(sex);
                           TextField Contact = new TextField();
                           DatePicker Hiredate = new DatePicker();
                           TextField Country = new TextField();   
                           ChoiceBox<String> City=new ChoiceBox<>();
                           City.getItems().addAll("AddisAbaba","Adama","DireDawa","Mekele","BahirDar","Gondar",
                                   "Hawassa","Desse","Harar");
         City.setValue("AddisAbaba");
         
          ChoiceBox<String> SubCity=new ChoiceBox<>();
                           SubCity.getItems().addAll("Bole","arada","AkakiKality","kirkos","Nefassilk","AdisKetema",
                                   "Keraniyo","Kolfe","Lideta");
         SubCity.setValue("Bole");
         
                           ChoiceBox<String> Position=new ChoiceBox<>();
                           Position.getItems().addAll("Data Analyst","Software Engineer","Project Manage","Operations Manager","Sales Manager","HR Manager (Human Resources Manager",
                                   "CTO (Chief Technology Officer)","CFO (Chief Financial Officer)","(CEO)Cheif Executive Officer");
           Position.setValue("(CEO)Cheif Executive Officer");
           
                               
         
         
           ChoiceBox<String> Department=new ChoiceBox<>();
                           Department.getItems().addAll("Administration","Customer Service","Information Technology","Marketing","Finance and Accounting","Human Resource",
                                   "Operations","Sales","Research and Development");
        Department.setValue("Information Technology");
        
          Button btnR=new Button("Register");
        Button btnC=new Button("Cancel");
        
         btnC.setOnAction(e->{
        Firstname.setText("");
        Middlename.setText("");
          Lastname.setText("");
          Email.setText("");
           Contact.setText("");
        Age.setText(null);
         Hiredate.setValue(null);
         Country.setText(null);
          City.setValue(null);
          SubCity.setValue(null);
            Position.setValue(null);
              Department.setValue(null);
        if(rbtM.isSelected())
            rbtM.selectedProperty().set(false);
        else if(rbtF.isSelected())
          rbtF.selectedProperty().set(false);  
       
        });
       
btnR.setOnAction(e -> {
    FileWriter fw = null;
    try {
        // Create a new file or append to the existing file
        File file = new File("C:\\Users\\Tehetena\\Desktop\\Employee\\employee.txt");
        fw = new FileWriter(file, true);

        // Get the values from the text fields and combo boxes
        String firstName = Firstname.getText();
        String middleName = Middlename.getText();
        String lastName = Lastname.getText();
        String email = Email.getText();
        String contact = Contact.getText();
        String ageText = Age.getText();
        int age = ageText.isEmpty() ? 0 : Integer.parseInt(ageText);
        String country = Country.getText();
        String city = City.getValue();
        String subCity = SubCity.getValue();
        String position = Position.getValue();
        String department = Department.getValue();

        // Determine the selected gender
        String gender;
        if (rbtM.isSelected()) {
            gender = "Male";
        } else if (rbtF.isSelected()) {
            gender = "Female";
        } else {
            gender = "Unknown";
        }

      
        fw.append(firstName + "," + middleName + "," + lastName + "," + email + "," + contact + "," + age + "," + gender + "," + country + "," + city + "," + subCity + "," + position + "," + department + "\n");
    } catch (IOException ex) {
        ex.printStackTrace();
    } finally {
        // Close the FileWriter
        try {
            if (fw != null) {
                fw.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Clear the form
    Firstname.setText("");
    Middlename.setText("");
    Lastname.setText("");
    Email.setText("");
    Contact.setText("");
    Age.setText(null);
    Country.setText(null);
    City.setValue(null);
    SubCity.setValue(null);
    Position.setValue(null);
    Department.setValue(null);

    if (rbtM.isSelected()) {
        rbtM.selectedProperty().set(false);
    } else if (rbtF.isSelected()) {
        rbtF.selectedProperty().set(false);
    }
});

  
              
              
           HBox hb1=new HBox(30);
        HBox hb2=new HBox(30);
        HBox hb3=new HBox(30);
        HBox hb4=new HBox(30);
        HBox hb5=new HBox(30);
        HBox hb6=new HBox(30);
        HBox hb7=new HBox(30);
        HBox hb8=new HBox(30);
        HBox hb9=new HBox(30);
        HBox hb10=new HBox(30);
        HBox hb11=new HBox(30);
         HBox hb12=new HBox(30);
          HBox hb13=new HBox(30);
          HBox hb14=new HBox(30);
        VBox vb1=new VBox(30);
        hb1.getChildren().addAll(empfirstname,Firstname);
        hb2.getChildren().addAll(empmiddlename,Middlename);
        hb3.getChildren().addAll(emplastname,Lastname);
        hb4.getChildren().addAll(empgender,rbtM,rbtF);
        hb5.getChildren().addAll(empemail,Email);
        hb6.getChildren().addAll(empage,Age);
         hb7.getChildren().addAll(empcontact,Contact);
         hb8.getChildren().addAll(emphireDate,Hiredate);
         hb9.getChildren().addAll(empcountry,Country);
         hb10.getChildren().addAll(empcity,City);
         hb11.getChildren().addAll(empsubcity,SubCity);
         hb12.getChildren().addAll(empdepartment,Department);
         hb13.getChildren().addAll(empposition,Position);
            hb14.getChildren().addAll(btnR,btnC);
        vb1.getChildren().addAll(hb1,hb2,hb3,hb4,hb5,hb6,hb7,hb8,hb9,hb10,hb11,hb12,hb13,hb14);
        vb1.setPadding(new Insets(20,20,20,20));
    
          vb1.getStyleClass().add("pane");
           btnR.getStyleClass().add("btn");
            return new StackPane(vb1);

    
    }
    
       
      private StackPane createViewPane() {
        // Write the code for the view page here
        TableView<Employee> tableView = new TableView<>();

    try {
        File file = new File("C:\\Users\\Tehetena\\Desktop\\Employee\\employee.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

      

        TableColumn<Employee, String> firstNameCol = new TableColumn<>("First Name");
        TableColumn<Employee, String> middleNameCol = new TableColumn<>("Middle Name");
        TableColumn<Employee, String> lastNameCol = new TableColumn<>("Last Name");
        TableColumn<Employee, String> emailCol = new TableColumn<>("Email");
        TableColumn<Employee, String> contactCol = new TableColumn<>("Contact");
        TableColumn<Employee, Integer> ageCol = new TableColumn<>("Age");
        TableColumn<Employee, String> genderCol = new TableColumn<>("Gender");
        TableColumn<Employee, String> countryCol = new TableColumn<>("Country");
        TableColumn<Employee, String> cityCol = new TableColumn<>("City");
        TableColumn<Employee, String> subCityCol = new TableColumn<>("Sub City");
        TableColumn<Employee, String> positionCol = new TableColumn<>("Position");
        TableColumn<Employee, String> departmentCol = new TableColumn<>("Department");

        // Set the cell value factories for the TableColumns
        
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        middleNameCol.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        subCityCol.setCellValueFactory(new PropertyValueFactory<>("subCity"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<>("department"));

        // Add the TableColumns to the TableView
        tableView.getColumns().addAll(firstNameCol, middleNameCol, lastNameCol, emailCol, contactCol, ageCol, genderCol, countryCol, cityCol, subCityCol, positionCol, departmentCol);

        // Create a list to store the employee data
      

  
      

       
    

        fr.close();
        br.close();
    } catch (IOException ex) {
        ex.printStackTrace();
    }

        return new StackPane(tableView);
    }


    // The update pane is created here
    private StackPane createUpdatePane() {
        // Write the code for the update page here
        VBox vbox = new VBox();
        vbox.getStyleClass().add("pane");
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Button updateButton = new Button("Update");
        updateButton.getStyleClass().add("btn");
        vbox.getChildren().addAll(nameLabel, nameField, updateButton);
        vbox.setSpacing(10);
        return new StackPane(vbox);
    }

    // The delete pane is created here
    private StackPane createDeletePane() {
        // Write the code for the delete page here
        VBox vbox = new VBox();
        vbox.getStyleClass().add("pane");
        Label idLabel = new Label("Employee Name:");
        TextField nameField = new TextField();
        Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("btn");
        vbox.getChildren().addAll(idLabel, nameField, deleteButton);
        vbox.setSpacing(10);
        deleteButton.setOnAction(e -> {
    String employeeName = nameField.getText();
    try {
        File file = new File("C:\\Users\\Tehetena\\Desktop\\Employee\\employee.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        // Create a temporary file to store the updated data
        File tempFile = new File("C:\\Users\\Tehetena\\Desktop\\Employee\\temp.txt");
        FileWriter fw = new FileWriter(tempFile);

        String line;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            String name = data[1];
            if (!name.equalsIgnoreCase(employeeName)) {
                fw.write(line + "\n");
            } else {
                found = true;
            }
        }

        fr.close();
        br.close();
        fw.close();

        if (found) {
            // Delete the original file and rename the temporary file
            file.delete();
            tempFile.renameTo(file);
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
});

        
        
        
        return new StackPane(vbox);
    }

    // This method is used to switch between the different panes
    private void showPane(StackPane pane) {
        root.setCenter(pane);
        currentPane = pane;
    }
    
    
  
    
    

    public static void main(String[] args) {
        launch(args);
    }
}


  
   
           