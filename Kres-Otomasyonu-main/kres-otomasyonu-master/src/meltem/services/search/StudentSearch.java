package meltem.services.search;

import meltem.controllers.StudentSearchController;
import meltem.controllers.UserInfoController;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.StudentRepository;
import meltem.services.data_access.concrete.UserRepository;
import meltem.services.logging.Logger;

public class StudentSearch {

    public StudentSearch() {

    }

    public void searchByName(String username) {

        StudentSearchController.student = StudentRepository.Instance.fetchByName(username);
        StudentSearchController.route = 1;
        if(StudentSearchController.student != null) {
            SceneBuilder.Instance.BuildScene("student_search");
        }

    }

    public void searchById(String id) {
        int idAsInteger = Integer.parseInt(id);
        Logger.LogDebug("" + idAsInteger);
        StudentSearchController.route = 1;
        StudentSearchController.student = StudentRepository.Instance.fetchById(idAsInteger);
        if(StudentSearchController.student != null) {
            SceneBuilder.Instance.BuildScene("student_search");
        }

    }

}
