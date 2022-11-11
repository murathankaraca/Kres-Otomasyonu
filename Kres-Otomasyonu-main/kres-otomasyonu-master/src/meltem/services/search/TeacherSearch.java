package meltem.services.search;

import meltem.controllers.TeacherInfoController;
import meltem.controllers.UserInfoController;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.TeacherRepository;
import meltem.services.data_access.concrete.UserRepository;
import meltem.services.logging.Logger;

public class TeacherSearch {

    public TeacherSearch() {

    }

    public void searchByUsername(String username) {

        TeacherInfoController.teacher = TeacherRepository.Instance.fetchByName(username);
        TeacherInfoController.route = 1;
        if(TeacherInfoController.teacher != null) {
            SceneBuilder.Instance.BuildScene("teacher_info");
        }

    }

    public void searchById(String id) {
        int idAsInteger = Integer.parseInt(id);
        Logger.LogDebug("" + idAsInteger);
        TeacherInfoController.route = 1;
        TeacherInfoController.teacher = TeacherRepository.Instance.fetchById(idAsInteger);
        if(TeacherInfoController.teacher != null) {
            SceneBuilder.Instance.BuildScene("teacher_info");
        }

    }

}
