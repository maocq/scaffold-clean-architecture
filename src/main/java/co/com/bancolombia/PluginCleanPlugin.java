
package co.com.bancolombia;

import co.com.bancolombia.models.TaskModel;
import co.com.bancolombia.task.*;
import org.gradle.api.Project;
import org.gradle.api.Plugin;
import org.gradle.api.Task;
import org.gradle.api.tasks.TaskContainer;

import java.util.ArrayList;
import java.util.List;

public class PluginCleanPlugin implements Plugin<Project> {
    private static String taskGroup = "Clean Architecture";

    public void apply(Project project) {
        List<TaskModel> tasks = initTasks() ;
        TaskContainer taskContainer = project.getTasks();

        for (TaskModel t : tasks) {
            Task temp = taskContainer.create(t.getName(), t.getTaskAction());
            taskContainer.create(t.getShortcut(), t.getTaskAction());
            temp.setGroup(t.getGroup());
            temp.setDescription(t.getDescription());
        }
    }

    private List<TaskModel> initTasks(){
        List<TaskModel> tasksModels = new ArrayList<>();
        tasksModels.add(new TaskModel("cleanArchitecture", "ca", "Scaffolding clean architecture project", taskGroup, GenerateStructureTask.class));
        tasksModels.add(new TaskModel("generateModel", "gm", "Generate model in domain layer", taskGroup, GenerateModelTask.class));
        tasksModels.add(new TaskModel("generateUseCase", "guc", "Generate use case in domain layer", taskGroup, GenerateUseCaseTask.class));
        tasksModels.add(new TaskModel("generateEntryPoint", "gep", "Generate entry point in infrastructure layer", taskGroup, GenerateEntryPointTask.class));
        tasksModels.add(new TaskModel("generateDrivenAdapter", "gda", "Generate driven adapter in infrastructure layer", taskGroup, GenerateDrivenAdapterTask.class));
        tasksModels.add(new TaskModel("validateStructure", "vs", "Validate that project references are not violated", taskGroup, ValidateStructureTask.class));
        return tasksModels;
    }
}
