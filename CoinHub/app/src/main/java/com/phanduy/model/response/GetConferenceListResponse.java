package com.phanduy.model.response;

import com.phanduy.model.ConversationModel;

import java.util.ArrayList;

/**
 * Created by duyuno on 11/14/16.
 */
public class GetConferenceListResponse extends ResponseObject{

    ArrayList<ConversationModel> listConferencePre;
    ArrayList<ConversationModel> listConferenceDoing;
    ArrayList<ConversationModel> listConferencePast;

    public ArrayList<ConversationModel> getListConferencePre() {
        return listConferencePre;
    }

    public ArrayList<ConversationModel> getListConferenceDoing() {
        return listConferenceDoing;
    }

    public ArrayList<ConversationModel> getListConferencePast() {
        return listConferencePast;
    }
}
