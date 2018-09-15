package com.phanduy.store;

import android.content.Context;
import android.net.Uri;

import com.phanduy.model.BabyInfo;
import com.phanduy.model.ColleagueGroup;
import com.phanduy.model.ColleagueModel;
import com.phanduy.model.CustomerModel;
import com.phanduy.model.LanguageModel;
import com.phanduy.model.MyCoin;
import com.phanduy.model.SpinnerModel;
import com.phanduy.model.UserProfile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.Pattern;

import com.phanduy.model.coinmarket.CoinModel;
import com.phanduy.coinhub.R;
import com.phanduy.model.coinmarket.GlobalMarket;


/**
 * GlobalValue class contains global static values
 */
public final class GlobalValue {

    private static GlobalValue instance = null;

    public static final String ACCOUNT_FB = "FB";
    public static final String ACCOUNT_IDOCTOR = "IDOCTOR";

    public static final String APP_NAME = "GNN_EXPRESS";

    public static BabyInfo currentBaby;
    public static UserProfile userProfile;
    public static Uri ringToneUri;
    public static int timeDataReload = 5, alertRepeatTime = 20;

    public static String currentDeviceUUID;

    public static ArrayList<LanguageModel> listLanguage;

    private static HashMap<String, String> hashMap;

    public static ArrayList<SpinnerModel> listGender;
    public static ArrayList<SpinnerModel> listTimeLoadData;

    public static ArrayList<ColleagueGroup> listColleageGroup;
    public static ArrayList<ColleagueModel> listColleageMember;
    public static ColleagueGroup currentCollectGroup;

    public static ArrayList<CustomerModel> listCustomer;

    public static ArrayList<CoinModel> listAllCoins;
    public static ArrayList<MyCoin> listMyCoins;
    public static HashMap<String, MyCoin> hashCoin;

    public static GlobalMarket globalMarket;

    static {
        listGender = new ArrayList<>();
        listGender.add(new SpinnerModel(0, "Nam"));
        listGender.add(new SpinnerModel(1, "Nữ"));
        listTimeLoadData = new ArrayList<>();
        listTimeLoadData.add(new SpinnerModel(30, "30 giây"));
        listTimeLoadData.add(new SpinnerModel(60, "1 phút"));
        listTimeLoadData.add(new SpinnerModel(90, "1.5 phút"));
        listTimeLoadData.add(new SpinnerModel(120, "2 phút"));
        listTimeLoadData.add(new SpinnerModel(300, "5 phút"));
    }

    public static void addListCoin(ArrayList<CoinModel> listCoin) {

        if(hashCoin == null) {
            hashCoin = new HashMap<>();
        }

        for(CoinModel coinMarketModel : listCoin) {
            if(hashCoin.containsKey(coinMarketModel.getSymbol())) {
                hashCoin.get(coinMarketModel.getSymbol()).copyData(coinMarketModel);
            } else {
                hashCoin.put(coinMarketModel.getSymbol(), (MyCoin) coinMarketModel);
            }

        }

    }

    public static void initHash() {
        if(listMyCoins == null) return;

        if(hashCoin == null) hashCoin = new HashMap<>();

        for(MyCoin myCoin : listMyCoins) {
            hashCoin.put(myCoin.getSymbol(), myCoin);
        }
    }

    public static Comparator myComparator = new Comparator<MyCoin>() {
        @Override
        public int compare(MyCoin lhs, MyCoin rhs) {
            // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
            return lhs.getUSDBalance() < rhs.getUSDBalance() ? 1 : (lhs.getUSDBalance() > rhs.getUSDBalance()) ? -1 : 0;
        }
    };

    public static void putCoinInfo(MyCoin myCoin) {
        if(hashCoin == null) {
            hashCoin = new HashMap<>();
        }
        if(listMyCoins == null) {
            listMyCoins = new ArrayList<>();
        }

        if(hashCoin.containsKey(myCoin.getSymbol())) {
            hashCoin.get(myCoin.getSymbol()).copyData(myCoin);
            for(MyCoin myCoin1 : GlobalValue.listMyCoins) {
                if(myCoin1.getId().equals(myCoin.getId())) {
                    myCoin1.copyData(myCoin);
                    break;
                }
            }
        } else {
            hashCoin.put(myCoin.getSymbol(), myCoin);
            listMyCoins.add(myCoin);
        }
        Collections.sort(listMyCoins, myComparator);
    }
    public static void deleteCoin(MyCoin myCoin) {
        if(hashCoin == null || hashCoin.isEmpty()) {
            return;
        }
        if(listMyCoins == null || listMyCoins.isEmpty()) {
            return;
        }

        hashCoin.remove(myCoin.getSymbol());

        for(int size = listMyCoins.size(), i = size - 1; i >= 0; i--) {

            MyCoin myCoin1 = listMyCoins.get(i);

            if(myCoin1.getId().equals(myCoin.getId())) {
                listMyCoins.remove(i);
//                if(i < listMyCoins.size()) {
//                    listMyCoins.get(i).setOpen(false);
//                }
                break;
            } else {
                myCoin1.setOpen(false);
            }
        }
    }

    public static GlobalValue getInstance() {
        if (instance == null) {
            instance = new GlobalValue();
        }
        return instance;
    }

    public static void removeGroup(ColleagueGroup colleagueGroup) {
        int index = -1;
        for (int i = 0, size = listColleageGroup.size(); i < size; i++) {
            ColleagueGroup cg = listColleageGroup.get(i);
            if (cg.getGroupId() == colleagueGroup.getGroupId()) {
                index = i;
                break;
            }
        }

        if (index >= 0)
            listColleageGroup.remove(index);

        if(currentCollectGroup != null && currentCollectGroup.getGroupId() == colleagueGroup.getGroupId()) {
            currentCollectGroup = null;
        }

        currentCollectGroup = getSelectedGroup();
    }

    public static ColleagueGroup getSelectedGroup() {

        if (currentCollectGroup != null) return currentCollectGroup;

        if (listColleageGroup == null || listColleageGroup.isEmpty()) {
            return null;
        }

        for (ColleagueGroup colleagueGroup : listColleageGroup) {
            if (colleagueGroup.isSelected()) {
                currentCollectGroup = colleagueGroup;
                return colleagueGroup;
            }
        }

        currentCollectGroup = listColleageGroup.get(0);
        currentCollectGroup.setSelected(true);
        return currentCollectGroup;
    }

    public static void changeCurrentGroup(ColleagueGroup colleagueGroup) {
        if (listColleageGroup == null || listColleageGroup.isEmpty() || colleagueGroup == null) return;

        for (ColleagueGroup colleagueGroup1 : listColleageGroup) {
            if (colleagueGroup1.getGroupId() == colleagueGroup.getGroupId()) {
                colleagueGroup1.setSelected(true);
                currentCollectGroup = colleagueGroup1;
            } else {
                colleagueGroup1.setSelected(false);
            }
        }
    }

    public static void initListColleagueMember() {
        if (listColleageGroup == null || listColleageGroup.isEmpty()) return;

        if (listColleageMember != null) {
            listColleageMember.clear();
        }

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (ColleagueGroup colleagueGroup : listColleageGroup) {
            if (colleagueGroup.getListMember() != null && !colleagueGroup.getListMember().isEmpty()) {
                if (listColleageMember == null) {
                    listColleageMember = new ArrayList<>();
                }

                for(ColleagueModel colleagueModel : colleagueGroup.getListMember()) {
                    if(!hashMap.containsKey(colleagueModel.getColleagueId())) {
                        listColleageMember.add(colleagueModel);
                        hashMap.put(colleagueModel.getColleagueId(), colleagueModel.getColleagueId());
                    }
                }

//                listColleageMember.addAll(colleagueGroup.getListMember());
            }
        }
    }

    public static int getGenderPosition(int gender) {
        for (int i = 0, size = listGender.size(); i < size; i++) {
            SpinnerModel genderModel = listGender.get(i);
            if (gender == genderModel.getValue()) {
                return i;
            }
        }

        return 0;
    }

    public static int getTimeDataReloadPosition(int time) {
        for (int i = 0, size = listTimeLoadData.size(); i < size; i++) {
            SpinnerModel genderModel = listTimeLoadData.get(i);
            if (time == genderModel.getValue()) {
                return i;
            }
        }

        return 0;
    }

    public static void clearData() {
        GlobalValue.currentBaby = null;
    }

    public static int getLanguagePosition(String name) {

        if (listLanguage == null) {
            return 0;
        }

        for (int i = 0, length = listLanguage.size(); i < length; i++) {
            LanguageModel languageModel = listLanguage.get(i);
            if (languageModel.getName().equals(name)) {
                return i;
            }
        }

        return 0;
    }

    public static String getDayDisplay(Context context, String schedule) {
        if (hashMap == null) {

            hashMap = new HashMap<>();

            String[] arrayDayToServer = context.getResources().getStringArray(R.array.dayOfWeeksToServer);
            String[] arrayDayDisplay = context.getResources().getStringArray(R.array.dayOfWeekDisplay);

            for (int i = 0, length = arrayDayToServer.length; i < length; i++) {
                hashMap.put(arrayDayToServer[i], arrayDayDisplay[i]);
            }
        }

        String[] arraySchedule = schedule.split(Pattern.quote("-"));
        if (arraySchedule == null || arraySchedule.length == 0) {
            return schedule;
        } else {
            String timeScheduleDisplay = "";
            for (int i = 0, length = arraySchedule.length; i < length; i++) {
                if (hashMap.get(arraySchedule[i]) != null) {
                    if (timeScheduleDisplay.length() == 0) {
                        timeScheduleDisplay += hashMap.get(arraySchedule[i]);
                    } else {
                        timeScheduleDisplay += ", " + hashMap.get(arraySchedule[i]);
                    }
                }
            }

            if (timeScheduleDisplay.length() == 0) {
                return schedule;
            } else {
                return timeScheduleDisplay;
            }
        }
    }
}
