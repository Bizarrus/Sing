package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.Invite;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InviteManager$ListInvitesResponse extends ParsedResponse {
    @JsonProperty("invites")
    public ArrayList<Invite> mInvites;
    @JsonProperty("next")
    public Integer mNext;

    public static InviteManager$ListInvitesResponse m7941a(NetworkResponse networkResponse) {
        return (InviteManager$ListInvitesResponse) ParsedResponse.m7676a(networkResponse, InviteManager$ListInvitesResponse.class);
    }

    public String toString() {
        return "ListInvitesResponse{next=" + this.mNext + ", mInvites=" + this.mInvites + '}';
    }
}
