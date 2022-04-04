package com.massivecraft.factions.integration.dynmap;

import com.massivecraft.factions.zcore.config.Config;

public class DynmapStyle {

    /**
     * @author FactionsUUID Team - Modified By CmdrKittens
     */

    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //

    public String lineColor = null;
    public Double lineOpacity = null;
    public Integer lineWeight = null;
    public String fillColor = null;
    public Double fillOpacity = null;
    public String homeMarker = null;
    public Boolean boost = null;

    @SafeVarargs
    public static <T> T coalesce(T... items) {
        for (T item : items) {
            if (item != null) {
                return item;
            }
        }
        return null;
    }

    public static int getColor(String string) {
        int ret = 0x00FF00;
        try {
            ret = Integer.parseInt(string.substring(1), 16);
        } catch (NumberFormatException ignored) {
        }
        return ret;
    }

    public int getLineColor() {
        return getColor(coalesce(this.lineColor, getDefault().lineColor, Config.DYNMAP_STYLE_LINE_COLOR.getString()));
    }

    public DynmapStyle setStrokeColor(String strokeColor) {
        this.lineColor = strokeColor;
        return this;
    }

    public double getLineOpacity() {
        return coalesce(this.lineOpacity, getDefault().lineOpacity, Config.DYNMAP_STYLE_LINE_OPACITY.getDouble());
    }

    public DynmapStyle setLineOpacity(Double strokeOpacity) {
        this.lineOpacity = strokeOpacity;
        return this;
    }

    public int getLineWeight() {
        return coalesce(this.lineWeight, getDefault().lineWeight, Config.DYNMAP_STYLE_LINE_WEIGHT.getInt());
    }

    public DynmapStyle setLineWeight(Integer strokeWeight) {
        this.lineWeight = strokeWeight;
        return this;
    }

    public int getFillColor() {
        return getColor(coalesce(this.fillColor, getDefault().fillColor, Config.DYNMAP_STYLE_FILL_COLOR.getString()));
    }

    public DynmapStyle setFillColor(String fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    public double getFillOpacity() {
        return coalesce(this.fillOpacity, getDefault().fillOpacity, Config.DYNMAP_STYLE_FILL_OPACITY.getDouble());
    }

    public DynmapStyle setFillOpacity(Double fillOpacity) {
        this.fillOpacity = fillOpacity;
        return this;
    }

    public String getHomeMarker() {
        return coalesce(this.homeMarker, getDefault().homeMarker, Config.DYNMAP_STYLE_HOME_MARKER.getString());
    }

    public DynmapStyle setHomeMarker(String homeMarker) {
        this.homeMarker = homeMarker;
        return this;
    }

    // -------------------------------------------- //
    // UTIL
    // -------------------------------------------- //

    public boolean getBoost() {
        return coalesce(this.boost, getDefault().boost, Config.DYNMAP_STYLE_BOOST.getOption());
    }

    public DynmapStyle setBoost(Boolean boost) {
        this.boost = boost;
        return this;
    }

    public DynmapStyle getDefault() {
        return new DynmapStyle()
                .setStrokeColor(Config.DYNMAP_STYLE_LINE_COLOR.getString())
                .setLineOpacity(Config.DYNMAP_STYLE_LINE_OPACITY.getDouble())
                .setLineWeight(Config.DYNMAP_STYLE_LINE_WEIGHT.getInt())
                .setFillColor(Config.DYNMAP_STYLE_FILL_COLOR.getString())
                .setFillOpacity(Config.DYNMAP_STYLE_FILL_OPACITY.getDouble())
                .setHomeMarker(Config.DYNMAP_STYLE_HOME_MARKER.getString())
                .setBoost(Config.DYNMAP_STYLE_BOOST.getOption());
    }

}
