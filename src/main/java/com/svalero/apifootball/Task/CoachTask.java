package com.svalero.apifootball.Task;

import com.svalero.apifootball.Model.Coach;
import com.svalero.apifootball.Model.Team;
import com.svalero.apifootball.Service.TeamService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class CoachTask extends Task<Integer> {

    private String nameCoach;
    private ObservableList<Coach> infoCoach;

    public CoachTask(String nameCoach, ObservableList<Coach> infoCoach){
        this.nameCoach = nameCoach;
        this.infoCoach = infoCoach;
    }
    @Override
    protected Integer call() throws Exception {
        updateMessage("Doing operation");
        TeamService teamService = new TeamService();

        System.out.println(nameCoach);

        Consumer<Coach> coachConsumer = (coach) -> {
            //Thread.sleep(20);
            Platform.runLater(() -> {
                infoCoach.add(coach);
                String team = coach.getTeam().getName();
                System.out.println(team);
            });
        };


        teamService.getCoachByName(nameCoach).subscribe(coachConsumer);

        return null;
    }
}
