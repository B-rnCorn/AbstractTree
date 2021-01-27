package com.project.client.controller;

import com.project.abstractTree.exceptions.TreeException;
import com.project.abstractTree.model.Task;
import com.project.abstractTree.model.Tree;
import com.project.client.TreeTaskCreator;
import com.project.client.TreeTaskReflector;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.FileReader;
import java.io.IOException;

public class MainController {
    @FXML
    private Label labelTaskName = new Label();
    @FXML
    private Button btn;
    @FXML
    private final Pane pane = new Pane();
    @FXML
    private TreeTableView<Task> treeTableView = new TreeTableView<Task>();
    @FXML
    private TreeTableColumn<Task, String> treeColumnTask;
    @FXML
    private TreeTableColumn<Task, Boolean> treeColumnActive;
    @FXML
    private TreeTableColumn<Task, String> treeColumnActiveTime;
    private Tree<Task> taskTree;
    Task emp;

    public MainController() {

    }

    @FXML
    private void initialize() {
        TreeTaskReflector treeTaskReflector = new TreeTaskReflector();
        treeTaskReflector.showTreeInTreeTableView(TreeTaskCreator.create(), treeTableView);
        /*
        treeTableView.setEditable(true);
        treeColumnTask = new TreeTableColumn<Task, String>("Задачи");
        treeColumnActive = new TreeTableColumn<Task, Boolean>("Активность");
        treeColumnActiveTime = new TreeTableColumn<Task, String>("Время активности");
        treeColumnTask.setMinWidth(150);
        treeTableView.getColumns().add(treeColumnTask);
        treeTableView.getColumns().add(treeColumnActive);
        treeTableView.getColumns().add(treeColumnActiveTime);
        TreeItem<Task> root = new TreeItem<Task>(taskTree.getRoot().getValue());
        treeTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        treeTableView.setRoot(root);
        TreeItem<Task> item = new TreeItem<Task>(new Task(1, "Чил"));
        root.getChildren().addAll(new TreeItem<Task>(new Task(2, "Лежать")),new TreeItem<Task>(new Task(3, "Сидеть")),new TreeItem<Task>(new Task(4, "Сидеть")));
        root.getChildren().add(item);
        treeColumnTask.setCellValueFactory(new TreeItemPropertyValueFactory<Task, String>("name"));
        treeColumnActive.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Task, Boolean>, ObservableValue<Boolean>>() {
            public ObservableValue<Boolean> call(TreeTableColumn.CellDataFeatures<Task, Boolean> param) {
                TreeItem<Task> treeItem = param.getValue();
                emp = treeItem.getValue();
                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(emp.isActive());

                booleanProp.addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (newValue) {
                            emp.Activate();
                        } else {
                            emp.Deactivate();
                            labelTaskName.setText(Long.toString(emp.getTimeDayActivity()));
                        }
                    }
                });
                return booleanProp;
            }
        });
        treeColumnActive.setCellFactory(new Callback<TreeTableColumn<Task, Boolean>, TreeTableCell<Task, Boolean>>() {
            public TreeTableCell<Task, Boolean> call(TreeTableColumn<Task, Boolean> param) {
                CheckBoxTreeTableCell<Task, Boolean> cell = new CheckBoxTreeTableCell<Task, Boolean>();
                cell.setEditable(true);
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
        treeColumnActiveTime.setCellValueFactory(new TreeItemPropertyValueFactory<Task, String>("timeDayActivity"));
        */
    }

    @FXML
    public void initTable() {
        Tree<Task> taskTree2 = new Tree<Task>();
        try {
            taskTree2.read(new FileReader("treeFile.json"));
        } catch (IOException e) {

        } catch (TreeException e) {

        }
        TreeItem<Task> root = new TreeItem<Task>(taskTree2.getRoot().getValue());
        treeTableView.setRoot(root);
        TreeItem<Task> item = new TreeItem<Task>(taskTree2.search(1).getValue());
        root.getChildren().add(item);
        treeColumnTask.setCellValueFactory(new TreeItemPropertyValueFactory<Task, String>("name"));
        /*
        taskTree.add(0, new Node<Task>(1, new Task(1,"Ютуб")));
        taskTree.add(0, new Node<Task>(10, new Task(10,"Лежать")));
        taskTree.add(1, new Node<Task>(6, new Task(6,"Развлечение")));
        try {
            FileWriter fileWriter = new FileWriter("treeFile.json",false);
            taskTree.write(fileWriter);
            fileWriter.close();
        }catch (IOException e){

        }catch (TreeException e){

        }
         */
    }
}
