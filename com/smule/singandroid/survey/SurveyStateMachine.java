package com.smule.singandroid.survey;

import com.smule.android.core.exception.SMError;
import com.smule.android.core.exception.SmuleException;
import com.smule.android.core.state_machine.ICommand;
import com.smule.android.core.state_machine.IState;
import com.smule.android.core.state_machine.StateMachine;
import com.smule.android.core.state_machine.StateMachine.CommonState;

public class SurveyStateMachine extends StateMachine {

    public enum Command implements ICommand {
        START_SURVEY,
        SKIP_RATING,
        START_RATING,
        SELECT_RATING,
        CANCEL_RATING,
        START_REASON,
        SELECT_REASON,
        COMMIT_REASON,
        CANCEL_REASON,
        VIEW_EXTRAS,
        COMPLETE_EXTRAS,
        CANCEL_EXTRAS,
        START_REPORT,
        COMPLETE_REPORT,
        FINISH_SURVEY;

        public String m25594a() {
            return SurveyStateMachine.m25596a(this);
        }
    }

    public enum State implements IState {
        SURVEY_STARTED,
        RATING_STARTED,
        RATING_COMPLETE,
        REASON_STARTED,
        HAVE_REASON,
        REASON_COMPLETE,
        REPORT_STARTED,
        REPORT_COMPLETE,
        EXTRAS_STARTED,
        EXTRAS_COMPLETE,
        FINISHED;

        public String m25595a() {
            return SurveyStateMachine.m25596a(this);
        }
    }

    public SurveyStateMachine() throws SmuleException {
        super(SurveyStateMachine.class);
        m17617a(Command.START_SURVEY, CommonState.UNKNOWN, SMError.NO_ERROR, State.SURVEY_STARTED);
        m17617a(Command.START_SURVEY, State.FINISHED, SMError.NO_ERROR, State.SURVEY_STARTED);
        m17617a(Command.SKIP_RATING, State.SURVEY_STARTED, SMError.NO_ERROR, State.REASON_STARTED);
        m17617a(Command.START_RATING, State.SURVEY_STARTED, SMError.NO_ERROR, State.RATING_STARTED);
        m17617a(Command.SELECT_RATING, State.RATING_STARTED, SMError.NO_ERROR, State.RATING_COMPLETE);
        m17617a(Command.START_REPORT, State.RATING_COMPLETE, SMError.NO_ERROR, State.REPORT_STARTED);
        m17617a(Command.COMPLETE_REPORT, State.REPORT_STARTED, SMError.NO_ERROR, State.REPORT_COMPLETE);
        m17617a(Command.VIEW_EXTRAS, State.REPORT_COMPLETE, SMError.NO_ERROR, State.EXTRAS_STARTED);
        m17617a(Command.COMPLETE_EXTRAS, State.EXTRAS_STARTED, SMError.NO_ERROR, State.EXTRAS_COMPLETE);
        m17617a(Command.START_REASON, State.RATING_COMPLETE, SMError.NO_ERROR, State.REASON_STARTED);
        m17617a(Command.SELECT_REASON, State.REASON_STARTED, SMError.NO_ERROR, State.HAVE_REASON);
        m17617a(Command.SELECT_REASON, State.HAVE_REASON, SMError.NO_ERROR, State.HAVE_REASON);
        m17617a(Command.COMMIT_REASON, State.HAVE_REASON, SMError.NO_ERROR, State.REASON_COMPLETE);
        m17617a(Command.START_REPORT, State.REASON_COMPLETE, SMError.NO_ERROR, State.REPORT_STARTED);
        m17617a(Command.CANCEL_RATING, State.RATING_STARTED, SMError.NO_ERROR, State.FINISHED);
        m17617a(Command.CANCEL_RATING, State.EXTRAS_STARTED, SMError.NO_ERROR, State.FINISHED);
        m17617a(Command.CANCEL_REASON, State.REASON_STARTED, SMError.NO_ERROR, State.REPORT_STARTED);
        m17617a(Command.CANCEL_REASON, State.HAVE_REASON, SMError.NO_ERROR, State.REPORT_STARTED);
        m17617a(Command.CANCEL_EXTRAS, State.EXTRAS_STARTED, SMError.NO_ERROR, State.FINISHED);
        m17618a(Command.FINISH_SURVEY, new State[]{State.RATING_STARTED, State.EXTRAS_COMPLETE, State.REPORT_COMPLETE}, SMError.NO_ERROR, State.FINISHED);
    }

    public static String m25596a(Enum enumR) {
        return enumR.getClass().getCanonicalName() + "." + enumR.name();
    }
}
