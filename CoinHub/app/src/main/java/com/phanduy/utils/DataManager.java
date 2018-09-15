package com.phanduy.utils;

import android.content.Context;
import android.view.Menu;

import com.phanduy.adapter.ParentsMenu;
import com.phanduy.adapter.holder.ColleagueItem;
import com.phanduy.model.ColleagueGroup;
import com.phanduy.model.ColleagueModel;
import com.phanduy.model.ConversationGroup;
import com.phanduy.model.ConversationMember;
import com.phanduy.model.ToolEntity;
import com.phanduy.model.response.GetConferenceListResponse;
import com.phanduy.coinhub.R;
import com.phanduy.view.fragment.market.MainMarketFragment;
import com.phanduy.view.fragment.portfolio.MainPortfolioFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataManager {

//	public static final HashMap<Integer, ToolEntity> hashMenu = new HashMap<>();
	public static final ArrayList<ToolEntity> listMenu = new ArrayList<>();

	public static void initMenu(Context context){
//		hashMenu.put(ToolEntity.MENU_PORT_FOLIO,new ToolEntity(ToolEntity.MENU_PORT_FOLIO, context.getResources().getString(R.string.labelPortFolio),R.drawable.ic_account_balance_wallet_white_24dp, false, MainPortfolioFragment.class.getName()));
//		hashMenu.put(ToolEntity.MENU_COIN_MARKET_CAP,new ToolEntity(ToolEntity.MENU_COIN_MARKET_CAP, context.getResources().getString(R.string.labelCoinMarketCap), R.drawable.ic_trending_up_white_24dp, MainMarketFragment.class.getName()));
//		hashMenu.put(ToolEntity.MENU_COIN_EVENT,new ToolEntity(ToolEntity.MENU_COIN_EVENT, context.getResources().getString(R.string.labelCoinEvent), R.drawable.ic_event_white_24dp, ""));
//		hashMenu.put(ToolEntity.MENU_ICOS,new ToolEntity(ToolEntity.MENU_ICOS, context.getResources().getString(R.string.labelIcos), R.drawable.ic_launch_white_24dp, ""));
//		hashMenu.put(ToolEntity.MENU_ABOUT,new ToolEntity(ToolEntity.MENU_ABOUT, context.getResources().getString(R.string.labelAbout), R.drawable.ic_info_outline_white_24dp, ""));

		listMenu.clear();
		listMenu.add(new ToolEntity(ToolEntity.MENU_PORT_FOLIO, context.getResources().getString(R.string.labelPortFolio),R.drawable.ic_account_balance_wallet_white_24dp, false, MainPortfolioFragment.class.getName()));
		ToolEntity coinMarketCap = new ToolEntity(ToolEntity.MENU_COIN_MARKET_CAP, context.getResources().getString(R.string.labelCoinMarketCap), R.drawable.ic_trending_up_white_24dp, MainMarketFragment.class.getName());

		ArrayList<ToolEntity> coinMarketCapChilds = new ArrayList<>();
		coinMarketCapChilds.add(new ToolEntity(ToolEntity.MENU_COIN_EVENT, context.getResources().getString(R.string.labelCoinEvent), R.drawable.ic_event_white_24dp, ""));
		coinMarketCapChilds.add(new ToolEntity(ToolEntity.MENU_COIN_EVENT, context.getResources().getString(R.string.labelCoinEvent), R.drawable.ic_event_white_24dp, ""));
		coinMarketCapChilds.add(new ToolEntity(ToolEntity.MENU_COIN_EVENT, context.getResources().getString(R.string.labelCoinEvent), R.drawable.ic_event_white_24dp, ""));
//		coinMarketCap.setListChilds(coinMarketCapChilds);

		listMenu.add(coinMarketCap);
		listMenu.add(new ToolEntity(ToolEntity.MENU_COIN_EVENT, context.getResources().getString(R.string.labelCoinEvent), R.drawable.ic_event_white_24dp, ""));
		listMenu.add(new ToolEntity(ToolEntity.MENU_ICOS, context.getResources().getString(R.string.labelIcos), R.drawable.ic_launch_white_24dp, ""));
		listMenu.add(new ToolEntity(ToolEntity.MENU_ABOUT, context.getResources().getString(R.string.labelAbout), R.drawable.ic_info_outline_white_24dp, ""));
	}

	public static ToolEntity getTopMenu() {
		ToolEntity toolEntity = listMenu.get(0);
		if(toolEntity.getListChilds() != null && !toolEntity.getListChilds().isEmpty()) {
			return toolEntity.getListChilds().get(0);
		} else {
			return toolEntity;
		}
	}

//	public static ArrayList<ToolEntity> genToolMenuEntities(Context context) {
//
//		if(hashMenu.isEmpty()) {
//			initMenu(context);
//		}
//
//		ArrayList<ToolEntity> list = new ArrayList<ToolEntity>();
//
//		for (Map.Entry<Integer, ToolEntity> entry : hashMenu.entrySet()) {
//			list.add(entry.getValue());
//		}
//
//		return list;
//	}

	public static ArrayList<ParentsMenu> genParentObj(ArrayList<ToolEntity> menu) {

		if(menu == null) {
			return null;
		}

		int size = menu.size();
		ArrayList<ParentsMenu> pmenuList = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			ParentsMenu tMenu = new ParentsMenu(menu.get(i), i == size - 1);
			pmenuList.add(tMenu);
		}
		return pmenuList;
	}

//	public static ToolEntity getMenu(int toolId) {
//		return hashMenu != null ? hashMenu.get(toolId) : null;
//	}

	static final SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
	static final SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

//	public static ArrayList<ConversationModel> genListConversationModel(int type) {
//		ArrayList<ConversationModel> list = new ArrayList<>();
//		for(int i = 0; i < 3; i++) {
//			ConversationModel conversationModel = new ConversationModel();
//			conversationModel.setName("Conversation " + i);
//			conversationModel.setMemberCount((i + 1));
//			conversationModel.setConversationType(type);
//
//			Calendar calendar = Calendar.getInstance();
//
//			conversationModel.setStartDate(calendar.getTime());
//			calendar.add(Calendar.HOUR_OF_DAY, 3);
//			conversationModel.setEndDate(calendar.getTime());
//
//			list.add(conversationModel);
//		}
//		return list;
//	}

	private static String downloadUrl(String strUrl) throws IOException {
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);

			// Creating an http connection to communicate with url
			urlConnection = (HttpURLConnection) url.openConnection();

			// Connecting to url
			urlConnection.connect();

			// Reading data from url
			iStream = urlConnection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

			StringBuffer sb = new StringBuffer();

			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			data = sb.toString();

			br.close();

		} catch (Exception e) {
//			Log.d("Exception while downloading url", e.toString());
		} finally {
			iStream.close();
			urlConnection.disconnect();
		}
		return data;
	}

}
