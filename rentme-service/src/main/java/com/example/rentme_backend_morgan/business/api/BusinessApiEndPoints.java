package com.example.rentme_backend_morgan.business.api;

public interface BusinessApiEndPoints {

    public static final String APP = "/app";
    public static String ADMIN = "/admin";
    public static String MANAGER = "/manager";

    //TODO USER
    public static String USER = "/user";

    public static String USER_GET_PROFILE = "/userGetProfile";
    public static String USER_EDIT_PROFILE = "/userEditProfile";
    public static String USER_FIND_PLACE = "/userFindPlace";
    public static String USER_GET_FULL_DATA_BY_PLACE = "/getFullDataByPlace";

    public static String USER_ADD_FAVORITES = "/userAddFavorites";
    public static String USER_GET_FAVORITES = "/userGetFavorites";
    public static String USER_REMOVE_FAVORITES = "/removeFavorite";

    public static String USER_ADD_HISTORY = "/userAddHistory";
    public static String USER_GET_HISTORY = "/userGetHistory";
    public static String USER_REMOVE_HISTORY = "/userRemoveHistory";

    public static String USER_REQUEST_TOUR = "/userRequestTour";

    public static String USER_APPLY_OWNER = "/userApplyOwner";
    public static String USER_MESSAGE_TO_OWNER = "/sendMessageToOwner";

    //TODO OWNER
    public static String OWNER = "/app/owner";

    public static String OWNER_ADD_ANNOUNCEMENT = "/addAnnouncement";
    public static String OWNER_ADD_RENT_OBJECT = "/addRealtyObject";
    public static String OWNER_GET_PROFILE = "/getProfile";
    public static String OWNER_EDIT_PROFILE = "/editProfile";
    public static String OWNER_EDIT_OBJECT = "/editObject";
    public static String OWNER_GET_OBJECTS = "/getObjects";
    public static String OWNER_REMOVE_OBJECT = "/removeObjects";
    public static String OWNER_SEND_MESSAGE = "/sendMessage";

    //TODO GUEST
    public static String GUEST = "/guest";
    public static String GUEST_FIND_PLACE = "/guestFindPlace";

}