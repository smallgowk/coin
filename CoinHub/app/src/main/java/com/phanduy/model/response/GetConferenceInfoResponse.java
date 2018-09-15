package com.phanduy.model.response;

import com.phanduy.model.ConversationMember;

import java.util.ArrayList;

/**
 * Created by duyuno on 11/14/16.
 */
public class GetConferenceInfoResponse extends ResponseObject{

    ArrayList<ConversationMember> listMember;

    public ArrayList<ConversationMember> getListMember() {
        return listMember;
    }
}
