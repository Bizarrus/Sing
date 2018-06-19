package com.smule.chat;

protected abstract class Chat$ChatPhasedTask<Params, Result> extends PriorityExecutor$PhasedTask<Params, Result> {
    private int f6989a;
    final /* synthetic */ Chat f6990d;

    @SafeVarargs
    Chat$ChatPhasedTask(Chat chat, Params... paramsArr) {
        this(chat, 0, paramsArr);
    }

    @SafeVarargs
    Chat$ChatPhasedTask(Chat chat, int i, Params... paramsArr) {
        this.f6990d = chat;
        super(paramsArr);
        this.f6989a = i;
    }

    public int m8447a() {
        return Chat.e(this.f6990d) ? 100 : this.f6989a;
    }
}
