package com.svalero.apifootball.Task;

import com.svalero.apifootball.Model.Team;
import com.svalero.apifootball.Service.TeamService;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

//Esta seria la clase de los consumidores de la api digamos
public class TeamTask extends Task<Integer> {

    private String country;
    private ObservableList<String> teamsNames;

    public TeamTask(String country, ObservableList<String> teamsNames){
        this.country = country;
        this.teamsNames = teamsNames;
    }

    @Override
    protected Integer call() throws Exception {
        updateMessage("Doing operation");
        TeamService teamService = new TeamService();
        //System.out.println(country + "TeamTask");

        final Long totalTeams = teamService.getTotalTeamsByCountry(country);
        //System.out.println(totalTeams);
        final int[] totalProcessedTeams = {0};

        Consumer<Team> teamConsumer = (team) -> {
          Thread.sleep(20);
            Platform.runLater(() -> {
                this.teamsNames.add(team.getName());
                totalProcessedTeams[0]++;
                double progressTeams = (double) totalProcessedTeams[0] / totalTeams;
                updateProgress(progressTeams, 1);
            });
        };

        teamService.getTeamsByCountry(country).subscribe(teamConsumer);

        return null;

    }
}
