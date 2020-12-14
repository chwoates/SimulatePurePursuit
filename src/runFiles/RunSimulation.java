package runFiles;

import displayFiles.DisplayPaths;

public class RunSimulation {

    RunSimulation() {
    }

    public static void main(String[] args) {
        RunSimulation run = new RunSimulation();
        run.executeSimulation();
    }

    public void executeSimulation() {
        System.out.println("Turbo");
        DisplayPaths displayPaths = new DisplayPaths();
        displayPaths.runDisplay();
    }
}
