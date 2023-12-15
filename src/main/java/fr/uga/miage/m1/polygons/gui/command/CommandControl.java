package fr.uga.miage.m1.polygons.gui.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public void addCommandFromHistory(){

        Command command = getOldCommand();
        addCommand(command);
    }

    public void removeCommand(Command command) {
        commands.remove(command);
    }

    public void removeLastCommandFromHistory() {
        commandsHistory.remove(commandsHistory.size()-1);
    }

    public List<Command> getCommands() {
        return commands;
    }

    public List<Command> getCommandsHistory() {
        return commandsHistory;
    }

    public Command getLastCommand(){
        return commands.get(commands.size()-1);
    }

    public Command getOldCommand(){
        return commandsHistory.get(commandsHistory.size()-1);
    }

    public void executeCommands() {

        Iterator<Command> iterator = commands.iterator();
        while (iterator.hasNext()) {
            Command command = iterator.next();
            if(command.execute()) commandsHistory.add(command);
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
