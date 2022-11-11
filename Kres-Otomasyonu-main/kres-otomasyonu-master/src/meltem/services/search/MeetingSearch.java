package meltem.services.search;

import meltem.controllers.MeetingInfoController;
import meltem.controllers.MeetingSearchController;
import meltem.controllers.TeacherInfoController;
import meltem.controllers.UserInfoController;
import meltem.models.Meeting;
import meltem.services.SceneBuilder;
import meltem.services.data_access.concrete.MeetingRepository;
import meltem.services.data_access.concrete.UserRepository;
import meltem.services.logging.Logger;

public class MeetingSearch {

    public MeetingSearch() {

    }

    public void searchByTitle(String username) {

        MeetingSearchController.meeting = MeetingRepository.Instance.fetchByTitle(username);
        MeetingSearchController.route = 1;
        if(MeetingSearchController.meeting != null) {
            SceneBuilder.Instance.BuildScene("meeting_search");
        }

    }

    public void searchById(String id) {
        int idAsInteger = Integer.parseInt(id);
        Logger.LogDebug("" + idAsInteger);
        MeetingSearchController.route = 1;
        MeetingSearchController.meeting = MeetingRepository.Instance.fetchById(idAsInteger);
        if(MeetingSearchController.meeting != null) {
            SceneBuilder.Instance.BuildScene("meeting_search");
        }

    }

}
