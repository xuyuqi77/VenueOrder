package com.xyq.vo.common.Tools;

import com.xyq.vo.model.Sport;
import com.xyq.vo.model.Venue;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */
public class Trans {
    /**
     * 转换venue.jsp
     *
     * @param str
     * @return
     */
    public static String toWord(String str) {
        if (str.equals("1")) {
            return "<p>\n" +
                    "                            <b>体育馆</b>\n" +
                    "                        </p>\n" +
                    "                        <p>\n" +
                    "                            这是体育馆。\n" +
                    "                        </p>";
        } else if (str.equals("2")) {
            return "<p>\n" +
                    "                            <b>篮球场</b>\n" +
                    "                        </p>\n" +
                    "                        <p>\n" +
                    "                            这是篮球场\n" +
                    "                        </p>";
        } else if (str.equals("3")) {
            return "<p>\n" +
                    "                            <b>北运动场</b>\n" +
                    "                        </p>\n" +
                    "                        <p>\n" +
                    "                            这是北运动场。\n" +
                    "                        </p>";
        } else if (str.equals("4")) {
            return "<p>\n" +
                    "                            <b>南运动场</b>\n" +
                    "                        </p>\n" +
                    "                        <p>\n" +
                    "                            这是南运动场。\n" +
                    "                        </p>";
        } else if (str.equals("5")) {
            return "<p>\n" +
                    "                            <b>网球场</b>\n" +
                    "                        </p>\n" +
                    "                        <p>\n" +
                    "                            这是网球场\n" +
                    "                        </p>";
        }
        return null;
    }

    public static String toPic(String str) {
        return "venue" + str + ".jpg";
    }

    /**
     * 转换picture.jsp
     *
     * @param str
     * @return
     */
    public static String toVenueName(String str) {
        if (str.equals("1")) {
            return "体育馆";
        } else if (str.equals("2")) {
            return "篮球场";
        } else if (str.equals("3")) {
            return "北运动场";
        } else if (str.equals("4")) {
            return "南运动场";
        } else if (str.equals("5")) {
            return "网球场";
        }
        return null;
    }

    public static String toVenuePic(String str) {
        if (str.equals("1")) {
            return "<div class=\"pic_small\">\n" +
                    "                    <img alt=\"\" src=\"images/pictures/teacher1.jpg\" title=\"1\" width=\"215px\" height=\"138px\">\n" +
                    "                    <p>1</p>\n" +
                    "                </div>" +
                    "<div class=\"pic_small\">\n" +
                    "                    <img alt=\"\" src=\"images/pictures/teacher2.jpg\" title=\"1\" width=\"215px\" height=\"138px\">\n" +
                    "                    <p>2</p>\n" +
                    "                </div>" +
                    "<div class=\"pic_small\">\n" +
                    "                    <img alt=\"\" src=\"images/pictures/teacher3.jpg\" title=\"1\" width=\"215px\" height=\"138px\">\n" +
                    "                    <p>3</p>\n" +
                    "                </div>" +
                    "<div class=\"pic_small\">\n" +
                    "                    <img alt=\"\" src=\"images/pictures/teacher4.jpg\" title=\"1\" width=\"215px\" height=\"138px\">\n" +
                    "                    <p>4</p>\n" +
                    "                </div>" +
                    "<div class=\"pic_small\">\n" +
                    "                    <img alt=\"\" src=\"images/pictures/teacher5.jpg\" title=\"1\" width=\"215px\" height=\"138px\">\n" +
                    "                    <p>5</p>\n" +
                    "                </div>";
        } else if (str.equals("3")) {
            return "<div class=\"pic_small\">\n" +
                    "    <img alt=\"\" src=\"images/pictures/ydh1.jpg\" title=\"1\" width=\"215px\" height=\"138px\"></a>\n" +
                    "    <p>1</p>\n" +
                    "</div>\n" +
                    "<div class=\"pic_small\">\n" +
                    "    <img alt=\"\" src=\"images/pictures/ydh2.jpg\" title=\"2\" width=\"215px\" height=\"138px\"></a>\n" +
                    "    <p>2</p>\n" +
                    "</div>\n" +
                    "<div class=\"pic_small\">\n" +
                    "    <img alt=\"\" src=\"images/pictures/ydh3.jpg\" title=\"3\" width=\"215px\" height=\"138px\"></a>\n" +
                    "    <p>3</p>\n" +
                    "</div>\n" +
                    "<div class=\"pic_small\">\n" +
                    "    <img alt=\"\" src=\"images/pictures/ydh4.jpg\" title=\"4\" width=\"215px\" height=\"138px\"></a>\n" +
                    "    <p>4</p>\n" +
                    "</div>\n" +
                    "<div class=\"pic_small\">\n" +
                    "    <img alt=\"\" src=\"images/pictures/ydh5.jpg\" title=\"5\" width=\"215px\" height=\"138px\"></a>\n" +
                    "    <p>5</p>\n" +
                    "</div>\n" +
                    "<div class=\"pic_small\">\n" +
                    "    <img alt=\"\" src=\"images/pictures/ydh6.jpg\" title=\"6\" width=\"215px\" height=\"138px\"></a>\n" +
                    "    <p>6</p>\n" +
                    "</div>\n";
        } else if (str.equals("2")) {
            return "<div class=\"pic_small\">\n" +
                    "    <img alt=\"\" src=\"images/pictures/bask1.jpg\" title=\"1\" width=\"215px\" height=\"138px\"></a>\n" +
                    "    <p>1</p>\n" +
                    "</div>\n" +
                    "<div class=\"pic_small\">\n" +
                    "    <img alt=\"\" src=\"images/pictures/bask2.jpg\" title=\"2\" width=\"215px\" height=\"138px\"></a>\n" +
                    "    <p>2</p>\n" +
                    "</div>\n" +
                    "<div class=\"pic_small\">\n" +
                    "    <img alt=\"\" src=\"images/pictures/bask3.jpg\" title=\"3\" width=\"215px\" height=\"138px\"></a>\n" +
                    "    <p>3</p>\n" +
                    "</div>";
        } else if (str.equals("4")) {
            return "<div class=\"pic_small\">\n" +
                    "    <img alt=\"\" src=\"images/pictures/bask4.jpg\" title=\"1\" width=\"215px\" height=\"138px\"></a>\n" +
                    "    <p>1</p>\n" +
                    "</div>\n" +
                    "<div class=\"pic_small\">\n" +
                    "    <img alt=\"\" src=\"images/pictures/bask5.jpg\" title=\"2\" width=\"215px\" height=\"138px\"></a>\n" +
                    "    <p>2</p>\n" +
                    "</div>\n" +
                    "<div class=\"pic_small\">\n" +
                    "    <img alt=\"\" src=\"images/pictures/bask6.jpg\" title=\"3\" width=\"215px\" height=\"138px\"></a>\n" +
                    "    <p>3</p>\n" +
                    "</div>";
        } else if (str.equals("5")) {
            return "<div class=\"pic_small\">\n" +
                    "    <img alt=\"\" src=\"images/pictures/ten1.jpg\" title=\"1\" width=\"215px\" height=\"138px\"></a>\n" +
                    "    <p>1</p>\n" +
                    "</div>\n" +
                    "<div class=\"pic_small\">\n" +
                    "    <img alt=\"\" src=\"images/pictures/ten2.jpg\" title=\"2\" width=\"215px\" height=\"138px\"></a>\n" +
                    "    <p>2</p>\n" +
                    "</div>\n" +
                    "<div class=\"pic_small\">\n" +
                    "    <img alt=\"\" src=\"images/pictures/ten3.jpg\" title=\"3\" width=\"215px\" height=\"138px\"></a>\n" +
                    "    <p>3</p>\n" +
                    "</div>";
        }
        return null;
    }

    /**
     * 转换order.jsp
     *
     * @param str
     * @return
     */
    public static String toOrderChooseVenue(String str, List<Venue> venueList) {
        if (StringUtils.isEmpty(str)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < venueList.size(); i++) {
                if (StringUtils.isNotEmpty(venueList.get(i).getVenue_name())) {
                    if (i != (venueList.size() - 1)) {
                        sb.append("<a href=\"/vo/afterchoose?c_venue=" + venueList.get(i).getVenue_name() + "\">" + venueList.get(i).getVenue_name() + "</a>\n");
                    } else {
                        sb.append("<a href=\"/vo/afterchoose?c_venue=" + venueList.get(i).getVenue_name() + "\">" + venueList.get(i).getVenue_name() + "</a>");
                    }
                }
            }
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < venueList.size(); i++) {
                if (StringUtils.isNotEmpty(venueList.get(i).getVenue_name())) {
                    if (i != (venueList.size() - 1)) {
                        if (venueList.get(i).getVenue_name().equals(str)) {
                            sb.append("<a href=\"/vo/afterchoose?c_venue=" + venueList.get(i).getVenue_name() + "\" class=\"cur\">" + venueList.get(i).getVenue_name() + "</a>\n");
                        } else {
                            sb.append("<a href=\"/vo/afterchoose?c_venue=" + venueList.get(i).getVenue_name() + "\">" + venueList.get(i).getVenue_name() + "</a>\n");
                        }
                    } else {
                        if (venueList.get(i).getVenue_name().equals(str)) {
                            sb.append("<a href=\"/vo/afterchoose?c_venue=" + venueList.get(i).getVenue_name() + "\" class=\"cur\">" + venueList.get(i).getVenue_name() + "</a>");
                        } else {
                            sb.append("<a href=\"/vo/afterchoose?c_venue=" + venueList.get(i).getVenue_name() + "\">" + venueList.get(i).getVenue_name() + "</a>");
                        }
                    }
                }
            }
            return sb.toString();
        }
    }

    public static String toOrderChooseSport(String str, List<Sport> sportList) {
        if (StringUtils.isEmpty(str)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < sportList.size(); i++) {
                if (StringUtils.isNotEmpty(sportList.get(i).getSport_name())) {
                    if (i != (sportList.size() - 1)) {
                        sb.append("<a href=\"/vo/afterchoose?c_sport=" + sportList.get(i).getSport_name() + "\">" + sportList.get(i).getSport_name() + "</a>\n");
                    } else {
                        sb.append("<a href=\"/vo/afterchoose?c_sport=" + sportList.get(i).getSport_name() + "\">" + sportList.get(i).getSport_name() + "</a>");
                    }
                }
            }
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < sportList.size(); i++) {
                if (StringUtils.isNotEmpty(sportList.get(i).getSport_name())) {
                    if (i != (sportList.size() - 1)) {
                        if (sportList.get(i).getSport_name().equals(str)) {
                            sb.append("<a href=\"/vo/afterchoose?c_sport=" + sportList.get(i).getSport_name() + "\" class=\"cur2\">" + sportList.get(i).getSport_name() + "</a>\n");
                        } else {
                            sb.append("<a href=\"/vo/afterchoose?c_sport=" + sportList.get(i).getSport_name() + "\">" + sportList.get(i).getSport_name() + "</a>\n");
                        }
                    } else {
                        if (sportList.get(i).getSport_name().equals(str)) {
                            sb.append("<a href=\"/vo/afterchoose?c_sport=" + sportList.get(i).getSport_name() + "\" class=\"cur2\">" + sportList.get(i).getSport_name() + "</a>");
                        } else {
                            sb.append("<a href=\"/vo/afterchoose?c_sport=" + sportList.get(i).getSport_name() + "\">" + sportList.get(i).getSport_name() + "</a>");
                        }
                    }
                }
            }
            return sb.toString();
        }
    }
}
