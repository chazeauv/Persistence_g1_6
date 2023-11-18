package fr.uga.miage.m1.polygons.gui.command;

import java.util.ArrayList;
import java.util.Iterator;

public class CommandControl {

    private ArrayList<Command> commands;

    private ArrayList<Command> commandsHistory;

    public CommandControl() {

        commands = new ArrayList<>();
        commandsHistory = new ArrayList<>();
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public Command getOldCommand(){
        return commandsHistory.get(commandsHistory.size()-1);
    }

    public void addCommands(){

        Command command = getOldCommand();

        if(command.getClass().getSimpleName().equalsIgnoreCase("drop")){
            addCommand(command);
            removeLastCommandFromHistory();
            addCommand(getOldCommand());
        } else {
            addCommand(command);
        }
        removeLastCommandFromHistory();
    }

    public void removeCommand(Command command) {
        commands.remove(command);
    }

    public void removeLastCommandFromHistory() {
        commandsHistory.remove(commandsHistory.size()-1);
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void executeCommands() {

        Iterator<Command> iterator = commands.iterator();
        while (iterator.hasNext()) {
            Command command = iterator.next();
            command.execute();
            commandsHistory.add(command);
            iterator.remove();
        }
    }

    public void undoCommands() {

        Iterator<Command> iterator = commands.iterator();
        while (iterator.hasNext()) {
            Command command = iterator.next();
            command.undo();
            commandsHistory.remove(command);
            iterator.remove();
        }
    }
}
