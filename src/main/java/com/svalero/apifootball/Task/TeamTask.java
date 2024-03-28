package com.svalero.apifootball.Task;

import com.svalero.apifootball.Model.Team;
import com.svalero.apifootball.Model.TeamVenueWrapper;
import com.svalero.apifootball.Model.Venue;
import com.svalero.apifootball.Service.TeamService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

//Esta seria la clase de los consumidores de la api digamos
public class TeamTask extends Task<Integer> {

    private String country;
    private ObservableList<Team> teamInfo;

    public TeamTask(String country, ObservableList<Team> teamInfo){
        this.country = country;
        this.teamInfo = teamInfo;
    }

    @Override
    protected Integer call() throws Exception {
        updateMessage("Doing operation");
        TeamService teamService = new TeamService();

        final Long totalTeams = teamService.getTotalTeamsByCountry(country);
        //System.out.println(totalTeams);
        final int[] totalProcessedTeams = {0};

        Consumer<Team> teamConsumer = (team) -> {
          Thread.sleep(20);
            Platform.runLater(() -> {
                teamInfo.add(team);
                totalProcessedTeams[0]++;
                double progressTeams = (double) totalProcessedTeams[0] / totalTeams;
                updateProgress(progressTeams, 1);
            });
        };

        teamService.getTeamsByCountry(country).subscribe(teamConsumer);

        return null;

    }
}
