package com.monisha.android.mytv.model;

import java.util.List;

/**
 * Created by monisha on 19/04/17.
 */

public class TvGuideEventsModel {


    /**
     * responseCode : 200
     * responseMessage : Success
     * getevent : [{"eventID":"24965150","channelId":1,"channelStbNumber":"411","channelHD":"false","channelTitle":"HBO","epgEventImage":null,"certification":"PG-13","displayDateTimeUtc":"2017-04-18 17:55:00.0","displayDateTime":"2017-04-19 01:55:00.0","displayDuration":"02:10:00","siTrafficKey":"1:998:30436663","programmeTitle":"Dance With Me","programmeId":"100317","episodeId":"","shortSynopsis":"An inspired but unschooled young man teaches a veteran dancer a few lessons about passion.","longSynopsis":null,"actors":"Vanessa Williams,Chayanne,Kris Kristofferson","directors":"Randa Haines","producers":"","genre":"Movie","subGenre":"Romance","live":false,"premier":false,"ottBlackout":false,"highlight":null,"contentId":null,"contentImage":null,"groupKey":null,"vernacularData":[]}]
     */

    private String responseCode;
    private String responseMessage;
    private List<GeteventBean> getevent;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<GeteventBean> getGetevent() {
        return getevent;
    }

    public void setGetevent(List<GeteventBean> getevent) {
        this.getevent = getevent;
    }

    public static class GeteventBean {
        /**
         * eventID : 24965150
         * channelId : 1
         * channelStbNumber : 411
         * channelHD : false
         * channelTitle : HBO
         * epgEventImage : null
         * certification : PG-13
         * displayDateTimeUtc : 2017-04-18 17:55:00.0
         * displayDateTime : 2017-04-19 01:55:00.0
         * displayDuration : 02:10:00
         * siTrafficKey : 1:998:30436663
         * programmeTitle : Dance With Me
         * programmeId : 100317
         * episodeId :
         * shortSynopsis : An inspired but unschooled young man teaches a veteran dancer a few lessons about passion.
         * longSynopsis : null
         * actors : Vanessa Williams,Chayanne,Kris Kristofferson
         * directors : Randa Haines
         * producers :
         * genre : Movie
         * subGenre : Romance
         * live : false
         * premier : false
         * ottBlackout : false
         * highlight : null
         * contentId : null
         * contentImage : null
         * groupKey : null
         * vernacularData : []
         */

        private String eventID;
        private int channelId;
        private String channelStbNumber;
        private String channelHD;
        private String channelTitle;
        private Object epgEventImage;
        private String certification;
        private String displayDateTimeUtc;
        private String displayDateTime;
        private String displayDuration;
        private String siTrafficKey;
        private String programmeTitle;
        private String programmeId;
        private String episodeId;
        private String shortSynopsis;
        private Object longSynopsis;
        private String actors;
        private String directors;
        private String producers;
        private String genre;
        private String subGenre;
        private boolean live;
        private boolean premier;
        private boolean ottBlackout;
        private Object highlight;
        private Object contentId;
        private Object contentImage;
        private Object groupKey;
        private List<?> vernacularData;

        public String getEventID() {
            return eventID;
        }

        public void setEventID(String eventID) {
            this.eventID = eventID;
        }

        public int getChannelId() {
            return channelId;
        }

        public void setChannelId(int channelId) {
            this.channelId = channelId;
        }

        public String getChannelStbNumber() {
            return channelStbNumber;
        }

        public void setChannelStbNumber(String channelStbNumber) {
            this.channelStbNumber = channelStbNumber;
        }

        public String getChannelHD() {
            return channelHD;
        }

        public void setChannelHD(String channelHD) {
            this.channelHD = channelHD;
        }

        public String getChannelTitle() {
            return channelTitle;
        }

        public void setChannelTitle(String channelTitle) {
            this.channelTitle = channelTitle;
        }

        public Object getEpgEventImage() {
            return epgEventImage;
        }

        public void setEpgEventImage(Object epgEventImage) {
            this.epgEventImage = epgEventImage;
        }

        public String getCertification() {
            return certification;
        }

        public void setCertification(String certification) {
            this.certification = certification;
        }

        public String getDisplayDateTimeUtc() {
            return displayDateTimeUtc;
        }

        public void setDisplayDateTimeUtc(String displayDateTimeUtc) {
            this.displayDateTimeUtc = displayDateTimeUtc;
        }

        public String getDisplayDateTime() {
            return displayDateTime;
        }

        public void setDisplayDateTime(String displayDateTime) {
            this.displayDateTime = displayDateTime;
        }

        public String getDisplayDuration() {
            return displayDuration;
        }

        public void setDisplayDuration(String displayDuration) {
            this.displayDuration = displayDuration;
        }

        public String getSiTrafficKey() {
            return siTrafficKey;
        }

        public void setSiTrafficKey(String siTrafficKey) {
            this.siTrafficKey = siTrafficKey;
        }

        public String getProgrammeTitle() {
            return programmeTitle;
        }

        public void setProgrammeTitle(String programmeTitle) {
            this.programmeTitle = programmeTitle;
        }

        public String getProgrammeId() {
            return programmeId;
        }

        public void setProgrammeId(String programmeId) {
            this.programmeId = programmeId;
        }

        public String getEpisodeId() {
            return episodeId;
        }

        public void setEpisodeId(String episodeId) {
            this.episodeId = episodeId;
        }

        public String getShortSynopsis() {
            return shortSynopsis;
        }

        public void setShortSynopsis(String shortSynopsis) {
            this.shortSynopsis = shortSynopsis;
        }

        public Object getLongSynopsis() {
            return longSynopsis;
        }

        public void setLongSynopsis(Object longSynopsis) {
            this.longSynopsis = longSynopsis;
        }

        public String getActors() {
            return actors;
        }

        public void setActors(String actors) {
            this.actors = actors;
        }

        public String getDirectors() {
            return directors;
        }

        public void setDirectors(String directors) {
            this.directors = directors;
        }

        public String getProducers() {
            return producers;
        }

        public void setProducers(String producers) {
            this.producers = producers;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public String getSubGenre() {
            return subGenre;
        }

        public void setSubGenre(String subGenre) {
            this.subGenre = subGenre;
        }

        public boolean isLive() {
            return live;
        }

        public void setLive(boolean live) {
            this.live = live;
        }

        public boolean isPremier() {
            return premier;
        }

        public void setPremier(boolean premier) {
            this.premier = premier;
        }

        public boolean isOttBlackout() {
            return ottBlackout;
        }

        public void setOttBlackout(boolean ottBlackout) {
            this.ottBlackout = ottBlackout;
        }

        public Object getHighlight() {
            return highlight;
        }

        public void setHighlight(Object highlight) {
            this.highlight = highlight;
        }

        public Object getContentId() {
            return contentId;
        }

        public void setContentId(Object contentId) {
            this.contentId = contentId;
        }

        public Object getContentImage() {
            return contentImage;
        }

        public void setContentImage(Object contentImage) {
            this.contentImage = contentImage;
        }

        public Object getGroupKey() {
            return groupKey;
        }

        public void setGroupKey(Object groupKey) {
            this.groupKey = groupKey;
        }

        public List<?> getVernacularData() {
            return vernacularData;
        }

        public void setVernacularData(List<?> vernacularData) {
            this.vernacularData = vernacularData;
        }
    }
}
