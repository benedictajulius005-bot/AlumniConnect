package Alumni.Project.service;

import Alumni.Project.dto.AnnouncementRequest;
import Alumni.Project.dto.response.AnnouncementResponse;
import Alumni.Project.dto.update.AnnouncementUpdateRequest;

import java.util.List;

public interface AnnouncementService {

    AnnouncementResponse createAnnouncement(
            AnnouncementRequest request
    );

    List<AnnouncementResponse>
    getAllAnnouncements();

    List<AnnouncementResponse>
    getActiveAnnouncements();

    AnnouncementResponse
    getAnnouncementById(
            Long id
    );

    List<AnnouncementResponse>
    getAnnouncementsByAdmin(
            Long adminId
    );

    AnnouncementResponse
    updateAnnouncement(
            Long id,
            AnnouncementUpdateRequest request
    );

    void deleteAnnouncement(
            Long id
    );
}