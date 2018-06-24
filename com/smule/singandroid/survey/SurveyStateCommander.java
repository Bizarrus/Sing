package com.smule.singandroid.survey;

import android.support.annotation.NonNull;
import com.smule.android.core.exception.SMError;
import com.smule.android.core.exception.SmuleException;
import com.smule.android.core.parameter.KeyedParameter;
import com.smule.android.core.parameter.ParameterHelper;
import com.smule.android.core.state_machine.ICommand;
import com.smule.android.core.state_machine.IState;
import com.smule.android.core.state_machine.StateMachine.CommonState;
import com.smule.android.logging.Log;
import com.smule.singandroid.survey.SurveyStateMachine.Command;
import com.smule.singandroid.survey.SurveyStateMachine.State;
import java.util.ArrayList;
import java.util.List;

public class SurveyStateCommander {
    public static final String f24338a = SurveyStateCommander.class.getName();
    protected SurveyStateMachine f24339b = new SurveyStateMachine();
    protected RatingInterface f24340c;
    protected ReasonInterface f24341d;

    public SurveyStateCommander() throws SmuleException {
        m25573u();
    }

    public IState m25574a() {
        return this.f24339b.m17614a();
    }

    public boolean m25577b() {
        return (m25574a() == CommonState.UNKNOWN || m25574a() == State.FINISHED) ? false : true;
    }

    public void m25578c() throws SmuleException {
        m25567a(Command.START_SURVEY, new KeyedParameter[0]);
    }

    public void m25579d() throws SmuleException {
        m25567a(Command.SKIP_RATING, new KeyedParameter[0]);
    }

    public void m25580e() throws SmuleException {
        m25567a(Command.START_RATING, new KeyedParameter[0]);
    }

    public void m25575a(@NonNull RatingInterface ratingInterface) throws SmuleException {
        m25567a(Command.SELECT_RATING, new KeyedParameter(SurveyParameterType.RATING.name(), ratingInterface));
    }

    public void m25581f() throws SmuleException {
        m25567a(Command.CANCEL_RATING, new KeyedParameter[0]);
    }

    public void m25582g() throws SmuleException {
        m25567a(Command.START_REASON, new KeyedParameter[0]);
    }

    public void m25576a(@NonNull ReasonInterface reasonInterface) throws SmuleException {
        m25567a(Command.SELECT_REASON, new KeyedParameter(SurveyParameterType.REASON.name(), reasonInterface));
    }

    public void m25583h() throws SmuleException {
        m25567a(Command.COMMIT_REASON, new KeyedParameter[0]);
    }

    public void m25584i() throws SmuleException {
        m25567a(Command.CANCEL_REASON, new KeyedParameter[0]);
    }

    public void m25585j() throws SmuleException {
        m25567a(Command.VIEW_EXTRAS, new KeyedParameter[0]);
    }

    public void m25586k() throws SmuleException {
        m25567a(Command.COMPLETE_EXTRAS, new KeyedParameter[0]);
    }

    public void m25587l() throws SmuleException {
        m25567a(Command.CANCEL_EXTRAS, new KeyedParameter[0]);
    }

    public void m25588m() throws SmuleException {
        m25567a(Command.START_REPORT, new KeyedParameter[0]);
    }

    public void m25589n() throws SmuleException {
        m25567a(Command.COMPLETE_REPORT, new KeyedParameter[0]);
    }

    public void m25590o() throws SmuleException {
        m25567a(Command.FINISH_SURVEY, new KeyedParameter[0]);
    }

    protected RatingInterface m25591p() {
        return this.f24340c;
    }

    protected ReasonInterface m25592q() {
        return this.f24341d;
    }

    private synchronized KeyedParameter[] m25567a(Command command, KeyedParameter... keyedParameterArr) throws SmuleException {
        this.f24339b.m17615a((ICommand) command);
        try {
            switch (command) {
                case START_SURVEY:
                    m25571s();
                    break;
                case SELECT_RATING:
                    m25566a(keyedParameterArr);
                    break;
                case SELECT_REASON:
                    m25569b(keyedParameterArr);
                    break;
                case CANCEL_REASON:
                    m25572t();
                    break;
            }
            m25565a(command);
            this.f24339b.m17616a((ICommand) command, SMError.NO_ERROR);
            m25568b(command, keyedParameterArr);
        } catch (SmuleException e) {
            this.f24339b.m17616a((ICommand) command, e.m17588a());
            throw e;
        }
        return null;
    }

    public static List<String> m25570r() {
        int i = 0;
        List<String> arrayList = new ArrayList();
        for (Command a : Command.values()) {
            arrayList.add(a.m25594a());
        }
        State[] values = State.values();
        int length = values.length;
        while (i < length) {
            arrayList.add(values[i].m25595a());
            i++;
        }
        return arrayList;
    }

    private void m25568b(Command command, KeyedParameter... keyedParameterArr) throws SmuleException {
        if (m25574a() instanceof Enum) {
            String a = SurveyStateMachine.m25596a((Enum) m25574a());
            switch (command) {
                case SELECT_RATING:
                    SurveyPresenter.m25515a(a, this.f24340c);
                    return;
                case SELECT_REASON:
                    SurveyPresenter.m25515a(a, this.f24341d);
                    return;
                default:
                    SurveyPresenter.m25517a(a, (Object[]) keyedParameterArr);
                    return;
            }
        }
        Log.e(f24338a, "Expect getState() to be an Enum");
    }

    private void m25565a(Command command) {
        Log.a(f24338a, "performAction_noOp_" + command.name());
    }

    private void m25571s() throws SmuleException {
        Log.b(f24338a, "performAction_startSurvey...");
        m25573u();
    }

    private void m25566a(KeyedParameter... keyedParameterArr) throws SmuleException {
        Log.b(f24338a, "performAction_selectRating...");
        if (keyedParameterArr == null || keyedParameterArr.length == 0) {
            throw new SmuleException(SurveyStateError.NO_RATING_SELECTED_ERROR, new KeyedParameter[0]);
        }
        this.f24340c = (RatingInterface) ParameterHelper.m17598a(SurveyParameterType.RATING.name(), keyedParameterArr);
        if (this.f24340c == null) {
            throw new SmuleException(SurveyStateError.NO_RATING_SELECTED_ERROR, keyedParameterArr);
        }
    }

    private void m25569b(KeyedParameter... keyedParameterArr) throws SmuleException {
        Log.b(f24338a, "performAction_selectReason...");
        if (keyedParameterArr == null || keyedParameterArr.length == 0) {
            throw new SmuleException(SurveyStateError.NO_REASON_SELECTED_ERROR, new KeyedParameter[0]);
        }
        this.f24341d = (ReasonInterface) ParameterHelper.m17598a(SurveyParameterType.REASON.name(), keyedParameterArr);
        if (this.f24341d == null) {
            throw new SmuleException(SurveyStateError.NO_REASON_SELECTED_ERROR, keyedParameterArr);
        }
    }

    private void m25572t() {
        Log.b(f24338a, "performAction_cancelReason...");
        this.f24341d = null;
    }

    private void m25573u() {
        this.f24340c = null;
        this.f24341d = null;
    }
}
