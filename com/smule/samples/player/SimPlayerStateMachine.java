package com.smule.samples.player;

import com.smule.android.core.exception.SMError;
import com.smule.android.core.exception.SmuleException;
import com.smule.android.core.state_machine.ICommand;
import com.smule.android.core.state_machine.IState;
import com.smule.android.core.state_machine.StateMachine;

public class SimPlayerStateMachine extends StateMachine {

    public enum Command implements ICommand {
        INITIALIZE,
        ON_INITIALIZE_COMPLETE,
        PLAY,
        ON_PLAY_COMPLETE,
        STOP,
        ON_STOP_COMPLETE,
        CLOSE
    }

    public enum State implements IState {
        NOT_INITIALIZED,
        INITIALIZING,
        IDLE,
        STARTING_PLAY,
        PLAYING,
        STOPPING_PLAY,
        SHUTTING_DOWN,
        SHUT_DOWN
    }

    public SimPlayerStateMachine() throws SmuleException {
        super(SimPlayerStateMachine.class, State.NOT_INITIALIZED);
        m17617a(Command.INITIALIZE, State.NOT_INITIALIZED, SMError.NO_ERROR, State.INITIALIZING);
        m17617a(Command.ON_INITIALIZE_COMPLETE, State.INITIALIZING, SMError.NO_ERROR, State.IDLE);
        m17617a(Command.ON_INITIALIZE_COMPLETE, State.INITIALIZING, SimPlayerError.COULD_NOT_INITIALIZE, State.NOT_INITIALIZED);
        m17617a(Command.PLAY, State.IDLE, SMError.NO_ERROR, State.STARTING_PLAY);
        m17617a(Command.ON_PLAY_COMPLETE, State.STARTING_PLAY, SMError.NO_ERROR, State.PLAYING);
        m17617a(Command.ON_PLAY_COMPLETE, State.STARTING_PLAY, SimPlayerError.COULD_NOT_START_PLAYER, State.IDLE);
        m17617a(Command.STOP, State.PLAYING, SMError.NO_ERROR, State.STOPPING_PLAY);
        m17617a(Command.ON_STOP_COMPLETE, State.STOPPING_PLAY, SMError.NO_ERROR, State.IDLE);
        m17617a(Command.ON_STOP_COMPLETE, State.STOPPING_PLAY, SimPlayerError.COULD_NOT_STOP_PLAYER, State.PLAYING);
        m17617a(Command.CLOSE, State.IDLE, SMError.NO_ERROR, State.SHUTTING_DOWN);
        m17617a(Command.CLOSE, State.SHUTTING_DOWN, SMError.NO_ERROR, State.SHUT_DOWN);
        m17617a(Command.CLOSE, State.SHUTTING_DOWN, SimPlayerError.COULD_NOT_CLOSE_PLAYER, State.IDLE);
    }
}
