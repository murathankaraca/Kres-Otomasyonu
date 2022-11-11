package meltem.services.search;

import meltem.controllers.UserInfoController;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.UserRepository;
import meltem.services.logging.Logger;

public class UserSearch {

    public UserSearch() {

    }

    public void searchByUsername(String username) {

        UserInfoController.user = UserRepository.Instance.fetchByUsername(username);
        UserInfoController.route = 1;
        if(UserInfoController.user != null) {
            SceneBuilder.Instance.BuildScene("user_info");
        }

    }

    public void searchById(String id) {
        int idAsInteger = Integer.parseInt(id);
        Logger.LogDebug("" + idAsInteger);
        UserInfoController.route = 1;
        UserInfoController.user = UserRepository.Instance.fetchById(idAsInteger);
        if(UserInfoController.user != null) {
            SceneBuilder.Instance.BuildScene("user_info");
        }

    }

}
