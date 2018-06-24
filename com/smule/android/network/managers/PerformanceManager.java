/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.logging.Log;
import com.smule.android.network.api.PerformancesAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.models.PerformancePost;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.response.GetConnectedPerformancesResponse;
import com.smule.android.utils.TimeExpiringLruCache;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import retrofit2.Call;

public class PerformanceManager {
    protected static PerformanceManager a;
    private static final String b;
    private com.smule.android.network.api.PerformancesAPI c = MagicNetwork.a().a(com.smule.android.network.api.PerformancesAPI.class);
    private TimeExpiringLruCache<String, PerformanceV2> d = new TimeExpiringLruCache(10, (int)TimeUnit.SECONDS.toMillis(30));

    static {
        b = PerformanceManager.class.getName();
        a = null;
    }

    private PerformanceManager() {
    }

    static /* synthetic */ com.smule.android.network.api.PerformancesAPI a(PerformanceManager performanceManager) {
        return performanceManager.c;
    }

    public static PerformanceManager a() {
        if (a == null) {
            a = new PerformanceManager();
        }
        return a;
    }

    public NetworkResponse a(String string2, PerformancesAPI renderType) {
        return NetworkUtils.a(this.c.renderPerformance(new SnpRequest(){
            public String performanceKey;
            public String renderType;

            public PerformancesAPI setPerformanceKey(String string2) {
                this.performanceKey = string2;
                return this;
            }

            public PerformancesAPI setRenderType(PerformancesAPI renderType) {
                if (renderType != null) {
                    this.renderType = renderType.toString();
                }
                return this;
            }
        }.setPerformanceKey(string2).setRenderType(renderType)));
    }

    public NetworkResponse a(String string2, String string3) {
        return NetworkUtils.a(this.c.deleteComments(new SnpRequest(){
            public String performanceKey;
            public ArrayList<String> postKeys;

            public PerformancesAPI setPerformanceKey(String string2) {
                this.performanceKey = string2;
                return this;
            }

            public PerformancesAPI setPostKey(String string2) {
                ArrayList<String> arrayList = new ArrayList<String>();
                arrayList.add(string2);
                return this.setPostKeys(arrayList);
            }

            public PerformancesAPI setPostKeys(ArrayList<String> arrayList) {
                this.postKeys = arrayList;
                return this;
            }
        }.setPerformanceKey(string2).setPostKey(string3)));
    }

    public NetworkResponse a(String string2, String string3, float f, float f2) {
        return NetworkUtils.a(this.c.comment(new SnpRequest(){
            public String comment;
            public Float latitude;
            public Float longitude;
            public String performanceKey;
            public Long replyToAccountId;

            public PerformancesAPI setComment(String string2) {
                this.comment = string2;
                return this;
            }

            public PerformancesAPI setLatAndLong(Float f, Float f2) {
                if (com.smule.android.utils.LocationUtils.a(f.floatValue(), f2.floatValue())) {
                    this.latitude = f;
                    this.longitude = f2;
                }
                return this;
            }

            public PerformancesAPI setPerformanceKey(String string2) {
                this.performanceKey = string2;
                return this;
            }

            public PerformancesAPI setReplyToAccountId(Long l) {
                this.replyToAccountId = l;
                return this;
            }
        }.setPerformanceKey(string2).setComment(string3).setLatAndLong(Float.valueOf(f), Float.valueOf(f2))));
    }

    public NetworkResponse a(String string2, String string3, float f, float f2, Long l) {
        return NetworkUtils.a(this.c.comment(new .setPerformanceKey(string2).setLatAndLong(Float.valueOf(f), Float.valueOf(f2)).setComment(string3).setReplyToAccountId(l)));
    }

    public NetworkResponse a(String string2, ArrayList<String> arrayList) {
        return NetworkUtils.a(this.c.deleteComments(new .setPerformanceKey(string2).setPostKeys(arrayList)));
    }

    public CreateOrJoinResponse a(String object, Float f, Float f2, String string2, Long l, Long l2, Long l3, Long l4, Integer n, Boolean bl, String string3, Boolean bl2, Float f3, Float f4) {
        object = new SnpRequest(){
            public Long audioResourceId;
            public Long coverResourceId;
            public Float latitude;
            public Float longitude;
            public Long metadataResourceId;
            public String performanceKey;
            public String trackFxMode;
            public String trackOptions;
            public Integer trackPartId;
            public Boolean trackVideo;
            public Boolean usedHeadphone;
            public Float vfactor;
            public Long videoResourceId;
            public Float vscore;

            public PerformancesAPI setAudioResourceId(Long l) {
                this.audioResourceId = l;
                return this;
            }

            public PerformancesAPI setCoverResourceId(Long l) {
                this.coverResourceId = l;
                return this;
            }

            public PerformancesAPI setLatAndLong(Float f, Float f2) {
                if (com.smule.android.utils.LocationUtils.a(f.floatValue(), f2.floatValue())) {
                    this.latitude = f;
                    this.longitude = f2;
                }
                return this;
            }

            public PerformancesAPI setMetadataResourceId(Long l) {
                this.metadataResourceId = l;
                return this;
            }

            public PerformancesAPI setPerformanceKey(String string2) {
                this.performanceKey = string2;
                return this;
            }

            public PerformancesAPI setTrackFxMode(String string2) {
                this.trackFxMode = string2;
                return this;
            }

            public PerformancesAPI setTrackOptions(String string2) {
                this.trackOptions = string2;
                return this;
            }

            public PerformancesAPI setTrackPartId(Integer n) {
                this.trackPartId = n;
                return this;
            }

            public PerformancesAPI setTrackVideo(Boolean bl) {
                this.trackVideo = bl;
                return this;
            }

            public PerformancesAPI setUsedHeadphone(Boolean bl) {
                this.usedHeadphone = bl;
                return this;
            }

            public PerformancesAPI setVfactor(Float f) {
                this.vfactor = f;
                return this;
            }

            public PerformancesAPI setVideoResourceId(Long l) {
                this.videoResourceId = l;
                return this;
            }

            public PerformancesAPI setVscore(Float f) {
                this.vscore = f;
                return this;
            }
        }.setPerformanceKey((String)object).setLatAndLong(f, f2).setTrackOptions(string2).setAudioResourceId(l).setCoverResourceId(l2).setMetadataResourceId(l3).setVideoResourceId(l4).setTrackPartId(n).setTrackVideo(bl).setTrackFxMode(string3).setUsedHeadphone(bl2).setVscore(f3).setVfactor(f4);
        return CreateOrJoinResponse.a(NetworkUtils.a(this.c.joinPerformance((Object)object)));
    }

    public CreateOrJoinResponse a(String object, Integer n, String string2, String string3, Long l, Boolean bl, Float f, Float f2, String string4, Long l2, Long l3, Long l4, Long l5, Integer n2, Boolean bl2, String string5, Boolean bl3, Float f3, Float f4, PerformancesAPI ensembleType) {
        object = new SnpRequest(){
            public String arrKey;
            public Integer arrVer;
            public Long audioResourceId;
            public PerformanceV2.CompositionType compType;
            public Long coverResourceId;
            public PerformancesAPI ensembleType;
            public Boolean isPrivate;
            public Float latitude;
            public Float longitude;
            public String message;
            public Long metadataResourceId;
            public Long seconds;
            public String songId;
            public String title;
            public String trackFxMode;
            public String trackOptions;
            public Integer trackPartId;
            public Boolean trackVideo;
            public Boolean usedHeadphone;
            public Float vfactor;
            public Long videoResourceId;
            public Float vscore;

            public PerformancesAPI setArrangement(String string2, int n) {
                if (string2 != null) {
                    this.arrKey = string2;
                    this.arrVer = n;
                }
                return this;
            }

            public PerformancesAPI setAudioResourceId(Long l) {
                this.audioResourceId = l;
                return this;
            }

            public PerformancesAPI setCompType(PerformanceV2.CompositionType compositionType) {
                this.compType = compositionType;
                return this;
            }

            public PerformancesAPI setCoverResourceId(Long l) {
                this.coverResourceId = l;
                return this;
            }

            public PerformancesAPI setEnsembleType(PerformancesAPI ensembleType) {
                this.ensembleType = ensembleType;
                return this;
            }

            public PerformancesAPI setLatAndLong(Float f, Float f2) {
                if (com.smule.android.utils.LocationUtils.a(f.floatValue(), f2.floatValue())) {
                    this.latitude = f;
                    this.longitude = f2;
                }
                return this;
            }

            public PerformancesAPI setMessage(String string2) {
                this.message = string2;
                return this;
            }

            public PerformancesAPI setMetadataResourceId(Long l) {
                this.metadataResourceId = l;
                return this;
            }

            public PerformancesAPI setPrivate(Boolean bl) {
                this.isPrivate = bl;
                return this;
            }

            public PerformancesAPI setSeconds(Long l) {
                this.seconds = l;
                return this;
            }

            public PerformancesAPI setSongId(String string2) {
                this.songId = string2;
                return this;
            }

            public PerformancesAPI setTitle(String string2) {
                this.title = string2;
                return this;
            }

            public PerformancesAPI setTrackFxMode(String string2) {
                this.trackFxMode = string2;
                return this;
            }

            public PerformancesAPI setTrackOptions(String string2) {
                this.trackOptions = string2;
                return this;
            }

            public PerformancesAPI setTrackPartId(Integer n) {
                this.trackPartId = n;
                return this;
            }

            public PerformancesAPI setTrackVideo(Boolean bl) {
                this.trackVideo = bl;
                return this;
            }

            public PerformancesAPI setUsedHeadphone(Boolean bl) {
                this.usedHeadphone = bl;
                return this;
            }

            public PerformancesAPI setVfactor(Float f) {
                this.vfactor = f;
                return this;
            }

            public PerformancesAPI setVideoResourceId(Long l) {
                this.videoResourceId = l;
                return this;
            }

            public PerformancesAPI setVscore(Float f) {
                this.vscore = f;
                return this;
            }
        }.setCompType(PerformanceV2.CompositionType.b).setArrangement((String)object, n).setTitle(string2).setMessage(string3).setSeconds(l).setPrivate(bl).setLatAndLong(f, f2).setTrackOptions(string4).setAudioResourceId(l2).setCoverResourceId(l3).setMetadataResourceId(l4).setVideoResourceId(l5).setTrackPartId(n2).setTrackVideo(bl2).setTrackFxMode(string5).setUsedHeadphone(bl3).setVscore(f3).setVfactor(f4).setEnsembleType(ensembleType);
        return CreateOrJoinResponse.a(NetworkUtils.a(this.c.createPerformance((Object)object)));
    }

    public PerformanceResponse a(String string2, float f, float f2) {
        return PerformanceResponse.a(NetworkUtils.a(this.c.listen(new SnpRequest(){
            public Float latitude;
            public Float longitude;
            public String performanceKey;

            public PerformancesAPI setLatAndLong(Float f, Float f2) {
                if (com.smule.android.utils.LocationUtils.a(f.floatValue(), f2.floatValue())) {
                    this.latitude = f;
                    this.longitude = f2;
                }
                return this;
            }

            public PerformancesAPI setPerformanceKey(String string2) {
                this.performanceKey = string2;
                return this;
            }
        }.setPerformanceKey(string2).setLatAndLong(Float.valueOf(f), Float.valueOf(f2)))));
    }

    public PerformanceResponse a(String string2, String string3, String string4, Boolean bl, Boolean bl2) {
        return PerformanceResponse.a(NetworkUtils.a(this.c.updatePerformance(new SnpRequest(){
            public Boolean close;
            public Boolean isPrivate;
            public String message;
            public String performanceKey;
            public String title;

            public PerformancesAPI setClose(Boolean bl) {
                this.close = bl;
                return this;
            }

            public PerformancesAPI setIsPrivate(Boolean bl) {
                this.isPrivate = bl;
                return this;
            }

            public PerformancesAPI setMessage(String string2) {
                this.message = string2;
                return this;
            }

            public PerformancesAPI setPerformanceKey(String string2) {
                this.performanceKey = string2;
                return this;
            }

            public PerformancesAPI setTitle(String string2) {
                this.title = string2;
                return this;
            }
        }.setPerformanceKey(string2).setClose(bl2).setIsPrivate(bl).setMessage(string4).setTitle(string3))));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public PerformanceResponse a(String object, boolean bl) {
        synchronized (this) {
            Object object2;
            block9 : {
                object2 = this.a((String)object);
                if (object2 != null && !bl) break block9;
                object = object2 = PerformanceResponse.a(NetworkUtils.a(this.c.getPerformanceDetailsNoRender(new SnpRequest(){
                    public boolean checkRestricted = false;
                    public String performanceKey;

                    public PerformancesAPI setCheckRestricted(boolean bl) {
                        this.checkRestricted = bl;
                        return this;
                    }

                    public PerformancesAPI setPerformanceKey(String string2) {
                        this.performanceKey = string2;
                        return this;
                    }
                }.setPerformanceKey((String)object).setCheckRestricted(bl))));
                if (!object2.a()) return object;
                object = object2;
                if (!object2.mPerformance.a()) return object;
                this.a(object2.mPerformance);
                return object2;
            }
            object = new PerformanceResponse();
            object.b();
            object.mPerformance = object2;
            return object;
        }
    }

    public PerformancesByPerformerResponse a(long l, String string2, PerformancesAPI fillStatus, Boolean bl, Integer n, Integer n2) {
        return PerformancesByPerformerResponse.a(NetworkUtils.a(this.c.getPerformancesByPerformer(new SnpRequest(){
            public Long accountId;
            public String app = MagicNetwork.b();
            public Boolean collapsed = false;
            public String fillStatus;
            public Integer limit;
            public Integer offset;

            public PerformancesAPI setAccountId(Long l) {
                if (l != null && l != 0) {
                    this.accountId = l;
                }
                return this;
            }

            public PerformancesAPI setApp(String string2) {
                if (!android.text.TextUtils.isEmpty((java.lang.CharSequence)string2)) {
                    this.app = string2;
                }
                return this;
            }

            public PerformancesAPI setFillStatus(PerformancesAPI fillStatus, Boolean bl) {
                block3 : {
                    block2 : {
                        if (fillStatus == null) break block2;
                        this.fillStatus = fillStatus.toString();
                        if (fillStatus != PerformancesAPI.FILLED || bl == null) break block3;
                        this.collapsed = bl;
                    }
                    return this;
                }
                this.collapsed = false;
                return this;
            }

            public PerformancesAPI setLimit(Integer n) {
                this.limit = n;
                return this;
            }

            public PerformancesAPI setOffset(Integer n) {
                this.offset = n;
                return this;
            }
        }.setAccountId(l).setApp(string2).setFillStatus(fillStatus, bl).setOffset(n).setLimit(n2))));
    }

    public PerformancesResponse a(int n) {
        return PerformancesResponse.a(NetworkUtils.a(this.c.getPerformanceList(new SnpRequest(){
            public String app;
            public String arrKey;
            public String fillStatus = PerformancesAPI.FILLED.toString();
            public String hotType;
            public Integer limit = 25;
            public String mixApp;
            public Integer offset = 0;
            public String songUid;
            public String sort = PerformancesAPI.RECENT.toString();
            public Boolean video;

            public PerformancesAPI setApp(String string2) {
                this.app = string2;
                return this;
            }

            public PerformancesAPI setArrKey(String string2) {
                this.arrKey = string2;
                return this;
            }

            public PerformancesAPI setFillStatus(PerformancesAPI fillStatus) {
                if (fillStatus != null) {
                    this.fillStatus = fillStatus.toString();
                }
                return this;
            }

            public PerformancesAPI setHotType(PerformancesAPI hotType) {
                if (hotType != null) {
                    this.hotType = hotType.toString();
                }
                return this;
            }

            public PerformancesAPI setLimit(Integer n) {
                if (n != null) {
                    this.limit = n;
                }
                return this;
            }

            public PerformancesAPI setMixApp(String string2) {
                this.mixApp = string2;
                return this;
            }

            public PerformancesAPI setOffset(Integer n) {
                if (n != null) {
                    this.offset = n;
                }
                return this;
            }

            public PerformancesAPI setSongUid(String string2) {
                this.songUid = string2;
                return this;
            }

            public PerformancesAPI setSort(PerformancesAPI sortOrder) {
                if (sortOrder != null) {
                    this.sort = sortOrder.toString();
                }
                return this;
            }

            public PerformancesAPI setVideoOnly(Boolean bl) {
                if (bl != null) {
                    this.video = bl;
                }
                return this;
            }
        }.setSort(PerformancesAPI.HOT).setOffset(n).setLimit(15))));
    }

    public PerformancesResponse a(long l, String string2) {
        return PerformancesResponse.a(NetworkUtils.a(this.c.getPerformances(new SnpRequest(){
            public Long accountId;
            public String app = MagicNetwork.b();
            public java.util.HashSet<String> forApps = MagicNetwork.d().getAppsInFamily();
            public Long playerId;

            public PerformancesAPI setAccountId(Long l) {
                this.accountId = l;
                return this;
            }

            public PerformancesAPI setApp(String string2) {
                if (!android.text.TextUtils.isEmpty((java.lang.CharSequence)string2)) {
                    this.app = string2;
                }
                return this;
            }

            public PerformancesAPI setPlayerId(Long l) {
                this.playerId = l;
                return this;
            }
        }.setAccountId(l).setApp(string2))));
    }

    public PerformancesResponse a(PerformancesAPI fillStatus, int n, int n2) {
        return PerformancesResponse.a(NetworkUtils.a(this.c.getPerformancesFromMyNetwork(new SnpRequest(){
            public String fillStatus;
            public Integer limit;
            public Integer offset;

            public PerformancesAPI setFillStatus(PerformancesAPI fillStatus) {
                this.fillStatus = fillStatus.toString();
                return this;
            }

            public PerformancesAPI setLimit(Integer n) {
                this.limit = n;
                return this;
            }

            public PerformancesAPI setOffset(Integer n) {
                this.offset = n;
                return this;
            }
        }.setFillStatus(fillStatus).setOffset(n).setLimit(n2))));
    }

    /*
     * Enabled aggressive block sorting
     */
    public PerformancesResponse a(PerformancesAPI object, Integer object2, Integer n, PerformancesAPI fillStatus, PerformancesAPI hotType, String string2, String string3, String string4, boolean bl, boolean bl2) {
        com.smule.android.network.api.PerformancesAPI performancesAPI = this.c;
        object2 = new .setSort((Object)object).setOffset((Integer)object2).setLimit(n).setFillStatus(fillStatus).setHotType(hotType);
        object = bl ? null : string2;
        object = object2.setSongUid((String)object);
        if (bl) {
            return PerformancesResponse.a(NetworkUtils.a(performancesAPI.getPerformanceList(object.setArrKey(string2).setApp(string3).setMixApp(string4).setVideoOnly(bl2))));
        }
        string2 = null;
        return PerformancesResponse.a(NetworkUtils.a(performancesAPI.getPerformanceList(object.setArrKey(string2).setApp(string3).setMixApp(string4).setVideoOnly(bl2))));
    }

    public PerformancesResponse a(Integer n, Integer n2) {
        return PerformancesResponse.a(NetworkUtils.a(this.c.getPerformanceList(new .setOffset(n).setLimit(n2).setSort(PerformancesAPI.HOT).setFillStatus(PerformancesAPI.FILLED).setSongUid("_open_mic_5m"))));
    }

    public PerformancesResponse a(Long l, String string2) {
        return PerformancesResponse.a(NetworkUtils.a(this.c.getPerformances(new .setPlayerId(l).setApp(string2))));
    }

    public PerformancesResponse a(String string2, Integer n, Integer n2) {
        return PerformancesResponse.a(NetworkUtils.a(this.c.getPerformancesByTag(new SnpRequest(){
            public java.util.HashSet<String> apps = MagicNetwork.d().getAppsInFamily();
            public Integer limit;
            public Integer offset;
            public String term;

            public PerformancesAPI setLimit(Integer n) {
                this.limit = n;
                return this;
            }

            public PerformancesAPI setOffset(Integer n) {
                this.offset = n;
                return this;
            }

            public PerformancesAPI setTerm(String string2) {
                this.term = string2;
                return this;
            }
        }.setTerm(string2).setOffset(n).setLimit(n2))));
    }

    public PerformancesResponse a(Collection<String> collection) {
        if (collection.size() > 25) {
            Log.d(b, "too many keys in request (" + collection.size() + ") - this request may fail");
        }
        return PerformancesResponse.a(NetworkUtils.a(this.c.getPerformanceDetailsByKeys(new SnpRequest(){
            public Collection<String> performanceKeys;

            public PerformancesAPI setPerformanceKeys(Collection<String> collection) {
                this.performanceKeys = collection;
                return this;
            }
        }.setPerformanceKeys(collection))));
    }

    public PerformanceV2 a(String string2) {
        return this.d.a(string2);
    }

    public Future<?> a(long l, Integer n, Integer n2,  performancesByPerformerResponseCallback) {
        return MagicNetwork.a(new Runnable(this, performancesByPerformerResponseCallback, l, n, n2){
            final /* synthetic */  a;
            final /* synthetic */ long b;
            final /* synthetic */ Integer c;
            final /* synthetic */ Integer d;
            final /* synthetic */ PerformanceManager e;
            {
                this.e = performanceManager;
                this.a = performancesByPerformerResponseCallback;
                this.b = l;
                this.c = n;
                this.d = n2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, PerformancesByPerformerResponse.a(NetworkUtils.a(PerformanceManager.a(this.e).getFavorites(new SnpRequest(){
                    public Long accountId;
                    public List<String> forApps = java.util.Arrays.asList(MagicNetwork.d().getAppUID());
                    public Integer limit;
                    public Integer offset;

                    public com.smule.android.network.api.PerformancesAPI$GetFavoritesRequest setAccountId(Long l) {
                        if (l != null && l != 0) {
                            this.accountId = l;
                        }
                        return this;
                    }

                    public com.smule.android.network.api.PerformancesAPI$GetFavoritesRequest setLimit(Integer n) {
                        this.limit = n;
                        return this;
                    }

                    public com.smule.android.network.api.PerformancesAPI$GetFavoritesRequest setOffset(Integer n) {
                        this.offset = n;
                        return this;
                    }
                }.setAccountId(this.b).setOffset(this.c).setLimit(this.d)))));
            }
        });
    }

    public Future<?> a(long l, String string2, PerformancesAPI fillStatus, Boolean bl, Integer n, Integer n2,  performancesByPerformerResponseCallback) {
        return MagicNetwork.a(new Runnable(this, performancesByPerformerResponseCallback, l, string2, fillStatus, bl, n, n2){
            final /* synthetic */  a;
            final /* synthetic */ long b;
            final /* synthetic */ String c;
            final /* synthetic */ PerformancesAPI d;
            final /* synthetic */ Boolean e;
            final /* synthetic */ Integer f;
            final /* synthetic */ Integer g;
            final /* synthetic */ PerformanceManager h;
            {
                this.h = performanceManager;
                this.a = performancesByPerformerResponseCallback;
                this.b = l;
                this.c = string2;
                this.d = fillStatus;
                this.e = bl;
                this.f = n;
                this.g = n2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.h.a(this.b, this.c, this.d, this.e, this.f, this.g));
            }
        });
    }

    public Future<?> a(PerformancesAPI sortOrder, Integer n, Integer n2, PerformancesAPI fillStatus, PerformancesAPI hotType, String string2, String string3, String string4, boolean bl, boolean bl2,  performancesResponseCallback) {
        return MagicNetwork.a(new Runnable(this, performancesResponseCallback, sortOrder, n, n2, fillStatus, hotType, string2, string3, string4, bl, bl2){
            final /* synthetic */  a;
            final /* synthetic */ PerformancesAPI b;
            final /* synthetic */ Integer c;
            final /* synthetic */ Integer d;
            final /* synthetic */ PerformancesAPI e;
            final /* synthetic */ PerformancesAPI f;
            final /* synthetic */ String g;
            final /* synthetic */ String h;
            final /* synthetic */ String i;
            final /* synthetic */ boolean j;
            final /* synthetic */ boolean k;
            final /* synthetic */ PerformanceManager l;
            {
                this.l = performanceManager;
                this.a = performancesResponseCallback;
                this.b = sortOrder;
                this.c = n;
                this.d = n2;
                this.e = fillStatus;
                this.f = hotType;
                this.g = string2;
                this.h = string3;
                this.i = string4;
                this.j = bl;
                this.k = bl2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.l.a(this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k));
            }
        });
    }

    public Future<PreuploadResponse> a(PerformancesAPI object, PerformancesAPI ensembleType, PerformanceV2.CompositionType compositionType, String string2, String string3, String string4, boolean bl) {
        object = new SnpRequest(){
            public String arrKey;
            public PerformanceV2.CompositionType compType;
            public PerformancesAPI ensembleType;
            public String performanceKey;
            public String seedKey;
            public String songId;
            public boolean trackVideo;
            public PerformancesAPI uploadType;

            public PerformancesAPI setArrKey(String string2) {
                this.arrKey = string2;
                return this;
            }

            public PerformancesAPI setCompType(PerformanceV2.CompositionType compositionType) {
                this.compType = compositionType;
                return this;
            }

            public PerformancesAPI setEnsembleType(PerformancesAPI ensembleType) {
                this.ensembleType = ensembleType;
                return this;
            }

            public PerformancesAPI setPerformanceKey(String string2) {
                this.performanceKey = string2;
                return this;
            }

            public PerformancesAPI setSeedKey(String string2) {
                this.seedKey = string2;
                return this;
            }

            public PerformancesAPI setSongId(String string2) {
                this.songId = string2;
                return this;
            }

            public PerformancesAPI setTrackVideo(boolean bl) {
                this.trackVideo = bl;
                return this;
            }

            public PerformancesAPI setUploadType(PerformancesAPI uploadType) {
                this.uploadType = uploadType;
                return this;
            }
        }.setUploadType((Object)object).setEnsembleType(ensembleType).setCompType(compositionType).setSongId(string2).setArrKey(string3).setSeedKey(string4).setTrackVideo(bl);
        return NetworkUtils.a(this.c.preupload((Object)object), PreuploadResponse.class);
    }

    public Future<?> a( connectedPerformancesResponseCallback) {
        return MagicNetwork.a(new Runnable(this, connectedPerformancesResponseCallback){
            final /* synthetic */  a;
            final /* synthetic */ PerformanceManager b;
            {
                this.b = performanceManager;
                this.a = connectedPerformancesResponseCallback;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.b.f());
            }
        });
    }

    public Future<?> a(Integer n, Integer n2,  performancesByPerformerResponseCallback) {
        return MagicNetwork.a(new Runnable(this, performancesByPerformerResponseCallback, n, n2){
            final /* synthetic */  a;
            final /* synthetic */ Integer b;
            final /* synthetic */ Integer c;
            final /* synthetic */ PerformanceManager d;
            {
                this.d = performanceManager;
                this.a = performancesByPerformerResponseCallback;
                this.b = n;
                this.c = n2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, PerformancesByPerformerResponse.a(NetworkUtils.a(PerformanceManager.a(this.d).getBookmarkSeed(new SnpRequest(){
                    public List<String> forApps = java.util.Arrays.asList(MagicNetwork.d().getAppUID());
                    public Integer limit;
                    public Integer offset;

                    public com.smule.android.network.api.PerformancesAPI$GetBookmarkSeedRequest setLimit(Integer n) {
                        this.limit = n;
                        return this;
                    }

                    public com.smule.android.network.api.PerformancesAPI$GetBookmarkSeedRequest setOffset(Integer n) {
                        this.offset = n;
                        return this;
                    }
                }.setOffset(this.b).setLimit(this.c)))));
            }
        });
    }

    public Future<?> a(String string2, float f, float f2, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.a(new Runnable(this, networkResponseCallback, string2, f, f2){
            final /* synthetic */ NetworkResponseCallback a;
            final /* synthetic */ String b;
            final /* synthetic */ float c;
            final /* synthetic */ float d;
            final /* synthetic */ PerformanceManager e;
            {
                this.e = performanceManager;
                this.a = networkResponseCallback;
                this.b = string2;
                this.c = f;
                this.d = f2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.e.b(this.b, this.c, this.d));
            }
        });
    }

    public Future<?> a(String string2, PerformancesAPI renderType, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.a(new Runnable(this, networkResponseCallback, string2, renderType){
            final /* synthetic */ NetworkResponseCallback a;
            final /* synthetic */ String b;
            final /* synthetic */ PerformancesAPI c;
            final /* synthetic */ PerformanceManager d;
            {
                this.d = performanceManager;
                this.a = networkResponseCallback;
                this.b = string2;
                this.c = renderType;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.d.a(this.b, this.c));
            }
        });
    }

    public Future<?> a(String string2, PerformancesAPI sortOrder, Integer n, Integer n2, PerformancesAPI fillStatus, Boolean bl,  performancesResponseCallback) {
        return this.a(sortOrder, n, n2, fillStatus, null, string2, null, null, true, bl, performancesResponseCallback);
    }

    public Future<?> a(String string2, PerformancesAPI sortOrder, Integer n, Integer n2, Boolean bl,  performancesResponseCallback) {
        return this.a(sortOrder, n, n2, PerformancesAPI.ACTIVESEED, null, string2, null, null, false, bl, performancesResponseCallback);
    }

    public Future<?> a(String string2, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.a(new Runnable(this, networkResponseCallback, string2){
            final /* synthetic */ NetworkResponseCallback a;
            final /* synthetic */ String b;
            final /* synthetic */ PerformanceManager c;
            {
                this.c = performanceManager;
                this.a = networkResponseCallback;
                this.b = string2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.c.f(this.b));
            }
        });
    }

    public Future<?> a(String string2,  isBookmarkSeedResponseCallback) {
        return MagicNetwork.a(new Runnable(this, isBookmarkSeedResponseCallback, string2){
            final /* synthetic */  a;
            final /* synthetic */ String b;
            final /* synthetic */ PerformanceManager c;
            {
                this.c = performanceManager;
                this.a = isBookmarkSeedResponseCallback;
                this.b = string2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, IsBookmarkSeedResponse.a(NetworkUtils.a(PerformanceManager.a(this.c).isBookmarkSeed(new SnpRequest(){
                    public String performanceKey;

                    public com.smule.android.network.api.PerformancesAPI$IsBookmarkSeedRequest setPerfKey(String string2) {
                        this.performanceKey = string2;
                        return this;
                    }
                }.setPerfKey(this.b)))));
            }
        });
    }

    public Future<?> a(String string2,  performanceLovesResponseCallback) {
        return MagicNetwork.a(new Runnable(this, performanceLovesResponseCallback, string2){
            final /* synthetic */  a;
            final /* synthetic */ String b;
            final /* synthetic */ PerformanceManager c;
            {
                this.c = performanceManager;
                this.a = performanceLovesResponseCallback;
                this.b = string2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.c.d(this.b));
            }
        });
    }

    public Future<?> a(String string2, Integer n, Integer n2,  performancePostsResponseCallback) {
        return MagicNetwork.a(new Runnable(this, performancePostsResponseCallback, string2, n, n2){
            final /* synthetic */  a;
            final /* synthetic */ String b;
            final /* synthetic */ Integer c;
            final /* synthetic */ Integer d;
            final /* synthetic */ PerformanceManager e;
            {
                this.e = performanceManager;
                this.a = performancePostsResponseCallback;
                this.b = string2;
                this.c = n;
                this.d = n2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.e.c(this.b, this.c, this.d));
            }
        });
    }

    public Future<?> a(String string2, Integer n, Integer n2,  performancesResponseCallback) {
        return MagicNetwork.a(new Runnable(this, performancesResponseCallback, string2, n, n2){
            final /* synthetic */  a;
            final /* synthetic */ String b;
            final /* synthetic */ Integer c;
            final /* synthetic */ Integer d;
            final /* synthetic */ PerformanceManager e;
            {
                this.e = performanceManager;
                this.a = performancesResponseCallback;
                this.b = string2;
                this.c = n;
                this.d = n2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.e.b(this.b, this.c, this.d));
            }
        });
    }

    public Future<?> a(String string2, String string3, String string4, Boolean bl, Boolean bl2,  performanceResponseCallback) {
        return MagicNetwork.a(new Runnable(this, performanceResponseCallback, string2, string3, string4, bl, bl2){
            final /* synthetic */  a;
            final /* synthetic */ String b;
            final /* synthetic */ String c;
            final /* synthetic */ String d;
            final /* synthetic */ Boolean e;
            final /* synthetic */ Boolean f;
            final /* synthetic */ PerformanceManager g;
            {
                this.g = performanceManager;
                this.a = performanceResponseCallback;
                this.b = string2;
                this.c = string3;
                this.d = string4;
                this.e = bl;
                this.f = bl2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.g.a(this.b, this.c, this.d, this.e, this.f));
            }
        });
    }

    public Future<?> a(String string2, boolean bl,  performanceResponseCallback) {
        return MagicNetwork.a(new Runnable(this, performanceResponseCallback, string2, bl){
            final /* synthetic */  a;
            final /* synthetic */ String b;
            final /* synthetic */ boolean c;
            final /* synthetic */ PerformanceManager d;
            {
                this.d = performanceManager;
                this.a = performanceResponseCallback;
                this.b = string2;
                this.c = bl;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.d.a(this.b, this.c));
            }
        });
    }

    public Future<?> a(List<String> list, List<String> list2, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.a(new Runnable(this, networkResponseCallback, list, list2){
            final /* synthetic */ NetworkResponseCallback a;
            final /* synthetic */ List b;
            final /* synthetic */ List c;
            final /* synthetic */ PerformanceManager d;
            {
                this.d = performanceManager;
                this.a = networkResponseCallback;
                this.b = list;
                this.c = list2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, NetworkUtils.a(PerformanceManager.a(this.d).updateFavorite(new SnpRequest(){
                    public List<String> add;
                    public List<String> remove;

                    public com.smule.android.network.api.PerformancesAPI$UpdateFavoritesRequest setAdd(List<String> list) {
                        this.add = list;
                        return this;
                    }

                    public com.smule.android.network.api.PerformancesAPI$UpdateFavoritesRequest setRemove(List<String> list) {
                        this.remove = list;
                        return this;
                    }
                }.setAdd(this.b).setRemove(this.c))));
            }
        });
    }

    public void a(PerformanceV2 performanceV2) {
        if (performanceV2 == null) {
            return;
        }
        this.d.a(performanceV2.performanceKey, performanceV2);
    }

    public NetworkResponse b(String string2, float f, float f2) {
        return NetworkUtils.a(this.c.love(new SnpRequest(){
            public Float latitude;
            public Float longitude;
            public String performanceKey;

            public PerformancesAPI setLatAndLong(Float f, Float f2) {
                if (com.smule.android.utils.LocationUtils.a(f.floatValue(), f2.floatValue())) {
                    this.latitude = f;
                    this.longitude = f2;
                }
                return this;
            }

            public PerformancesAPI setPerformanceKey(String string2) {
                this.performanceKey = string2;
                return this;
            }
        }.setPerformanceKey(string2).setLatAndLong(Float.valueOf(f), Float.valueOf(f2))));
    }

    public NetworkResponse b(String string2, String string3) {
        return NetworkUtils.a(this.c.reportAbuses(new SnpRequest(){
            public String performanceKey;
            public ArrayList<String> postKeys;

            public PerformancesAPI setPerformanceKey(String string2) {
                this.performanceKey = string2;
                return this;
            }

            public PerformancesAPI setPostKey(String string2) {
                ArrayList<String> arrayList = new ArrayList<String>();
                arrayList.add(string2);
                return this.setPostKeys(arrayList);
            }

            public PerformancesAPI setPostKeys(ArrayList<String> arrayList) {
                this.postKeys = arrayList;
                return this;
            }
        }.setPerformanceKey(string2).setPostKey(string3)));
    }

    public NetworkResponse b(String string2, ArrayList<String> arrayList) {
        return NetworkUtils.a(this.c.reportAbuses(new .setPerformanceKey(string2).setPostKeys(arrayList)));
    }

    @Deprecated
    public PerformanceResponse b(String string2) {
        return PerformanceResponse.a(NetworkUtils.a(this.c.getPerformanceDetails(new .setPerformanceKey(string2))));
    }

    public PerformancesResponse b() {
        return PerformancesResponse.a(NetworkUtils.a(this.c.getPerformanceList(new .setSort(PerformancesAPI.HOT))));
    }

    public PerformancesResponse b(int n) {
        return this.a(n);
    }

    public PerformancesResponse b(String string2, Integer n, Integer n2) {
        return PerformancesResponse.a(NetworkUtils.a(this.c.getPerformanceChildren(new SnpRequest(){
            public Integer limit = 25;
            public Integer offset = 0;
            public String performanceKey;

            public PerformancesAPI setLimit(Integer n) {
                if (n != null) {
                    this.limit = n;
                }
                return this;
            }

            public PerformancesAPI setOffset(Integer n) {
                if (n != null) {
                    this.offset = n;
                }
                return this;
            }

            public PerformancesAPI setPerformanceKey(String string2) {
                this.performanceKey = string2;
                return this;
            }
        }.setPerformanceKey(string2).setOffset(n).setLimit(n2))));
    }

    public PreuploadResponse b(String object, boolean bl) {
        object = new .setUploadType(PerformancesAPI.UPDATE).setPerformanceKey((String)object).setTrackVideo(bl);
        return PreuploadResponse.a(NetworkUtils.a(this.c.preupload((Object)object)));
    }

    public Future<?> b(String string2, PerformancesAPI sortOrder, Integer n, Integer n2, Boolean bl,  performancesResponseCallback) {
        return this.a(sortOrder, n, n2, PerformancesAPI.SEED, null, string2, null, null, true, bl, performancesResponseCallback);
    }

    public Future<?> b(String string2, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.a(new Runnable(this, string2, networkResponseCallback){
            final /* synthetic */ String a;
            final /* synthetic */ NetworkResponseCallback b;
            final /* synthetic */ PerformanceManager c;
            {
                this.c = performanceManager;
                this.a = string2;
                this.b = networkResponseCallback;
            }

            public void run() {
                NetworkResponse networkResponse = NetworkUtils.a(PerformanceManager.a(this.c).playPerformance(new SnpRequest(){
                    public String performanceKey;

                    public com.smule.android.network.api.PerformancesAPI$PlayPerformanceRequest setPerformanceKey(String string2) {
                        this.performanceKey = string2;
                        return this;
                    }
                }.setPerformanceKey(this.a)));
                if (this.b != null) {
                    com.smule.android.network.core.CoreUtil.a(this.b, networkResponse);
                }
            }
        });
    }

    public Future<?> b(List<String> list, List<String> list2, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.a(new Runnable(this, networkResponseCallback, list, list2){
            final /* synthetic */ NetworkResponseCallback a;
            final /* synthetic */ List b;
            final /* synthetic */ List c;
            final /* synthetic */ PerformanceManager d;
            {
                this.d = performanceManager;
                this.a = networkResponseCallback;
                this.b = list;
                this.c = list2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, NetworkUtils.a(PerformanceManager.a(this.d).updateBookmark(new SnpRequest(){
                    public List<String> add;
                    public List<String> remove;

                    public com.smule.android.network.api.PerformancesAPI$UpdateBookmarkRequest setAdd(List<String> list) {
                        this.add = list;
                        return this;
                    }

                    public com.smule.android.network.api.PerformancesAPI$UpdateBookmarkRequest setRemove(List<String> list) {
                        this.remove = list;
                        return this;
                    }
                }.setAdd(this.b).setRemove(this.c))));
            }
        });
    }

    public PerformancePostsResponse c(String string2, Integer n, Integer n2) {
        return PerformancePostsResponse.a(NetworkUtils.a(this.c.getComments(new SnpRequest(){
            public Integer limit;
            public Integer offset;
            public String performanceKey;

            public PerformancesAPI setLimit(Integer n) {
                this.limit = n;
                return this;
            }

            public PerformancesAPI setOffset(Integer n) {
                this.offset = n;
                return this;
            }

            public PerformancesAPI setPerformanceKey(String string2) {
                this.performanceKey = string2;
                return this;
            }
        }.setPerformanceKey(string2).setOffset(n).setLimit(n2))));
    }

    public PerformancesResponse c() {
        return this.b();
    }

    public PerformancesResponse c(String string2) {
        return PerformancesResponse.a(NetworkUtils.a(this.c.getPerformancesByPerformer(new .setAccountId(0).setApp(string2))));
    }

    public Future<?> c(String string2, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.a(new Runnable(this, string2, networkResponseCallback){
            final /* synthetic */ String a;
            final /* synthetic */ NetworkResponseCallback b;
            final /* synthetic */ PerformanceManager c;
            {
                this.c = performanceManager;
                this.a = string2;
                this.b = networkResponseCallback;
            }

            public void run() {
                NetworkResponse networkResponse = NetworkUtils.a(PerformanceManager.a(this.c).listenStart(new SnpRequest(){
                    public String performanceKey;

                    public com.smule.android.network.api.PerformancesAPI$ListenStartRequest setPerformanceKey(String string2) {
                        this.performanceKey = string2;
                        return this;
                    }
                }.setPerformanceKey(this.a)));
                if (this.b != null) {
                    com.smule.android.network.core.CoreUtil.a(this.b, networkResponse);
                }
            }
        });
    }

    public PerformanceLovesResponse d(String string2) {
        return PerformanceLovesResponse.a(NetworkUtils.a(this.c.getLoves(new SnpRequest(){
            public String performanceKey;

            public PerformancesAPI setPerformanceKey(String string2) {
                this.performanceKey = string2;
                return this;
            }
        }.setPerformanceKey(string2))));
    }

    public PerformancesResponse d() {
        return PerformancesResponse.a(NetworkUtils.a(this.c.getPerformanceList(new .setSort(PerformancesAPI.RECENT))));
    }

    public Future<?> d(String string2, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.a(new Runnable(this, string2, networkResponseCallback){
            final /* synthetic */ String a;
            final /* synthetic */ NetworkResponseCallback b;
            final /* synthetic */ PerformanceManager c;
            {
                this.c = performanceManager;
                this.a = string2;
                this.b = networkResponseCallback;
            }

            public void run() {
                NetworkResponse networkResponse = NetworkUtils.a(PerformanceManager.a(this.c).logRecCompletedArr(new SnpRequest(){
                    public String arrKey;

                    public com.smule.android.network.api.PerformancesAPI$LogRecCompletedArrRequest setArrangementKey(String string2) {
                        this.arrKey = string2;
                        return this;
                    }
                }.setArrangementKey(this.a)));
                if (this.b != null) {
                    com.smule.android.network.core.CoreUtil.a(this.b, networkResponse);
                }
            }
        });
    }

    public NetworkResponse e(String string2) {
        return NetworkUtils.a(this.c.deletePerformance(new SnpRequest(){
            public String performanceKey;

            public PerformancesAPI setPerformanceKey(String string2) {
                this.performanceKey = string2;
                return this;
            }
        }.setPerformanceKey(string2)));
    }

    @Deprecated
    public PerformancesResponse e() {
        return PerformancesResponse.a(NetworkUtils.a(this.c.getPerformancesByFeed(new SnpRequest(){
            public List<String> forApps = java.util.Arrays.asList(MagicNetwork.d().getAppUID());
        })));
    }

    public NetworkResponse f(String string2) {
        return NetworkUtils.a(this.c.reportAbusivePerformance(new SnpRequest(){
            public String performanceKey;

            public PerformancesAPI setPerformanceKey(String string2) {
                this.performanceKey = string2;
                return this;
            }
        }.setPerformanceKey(string2)));
    }

    public GetConnectedPerformancesResponse f() {
        return GetConnectedPerformancesResponse.a(NetworkUtils.a(this.c.getConnectedPerformances(new SnpRequest())), GetConnectedPerformancesResponse.class);
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class CreateOrJoinResponse
    extends ParsedResponse {
        @JsonProperty(value="performance")
        public PerformanceV2 mPerformance;
        @JsonProperty(value="trackKey")
        public String mTrackKey;

        static CreateOrJoinResponse a(NetworkResponse networkResponse) {
            return CreateOrJoinResponse.a(networkResponse, CreateOrJoinResponse.class);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class IsBookmarkSeedResponse
    extends ParsedResponse {
        @JsonProperty(value="isBookmarkSeed")
        public Boolean mIsBookmarkSeed;

        static IsBookmarkSeedResponse a(NetworkResponse networkResponse) {
            return IsBookmarkSeedResponse.a(networkResponse, IsBookmarkSeedResponse.class);
        }

        public String toString() {
            return "IsBookmarkSeedResponse{mIsBookmarkSeed=" + this.mIsBookmarkSeed + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class PerformanceLovesResponse
    extends ParsedResponse {
        @JsonProperty(value="performanceKey")
        public String mPerformanceKey;
        @JsonProperty(value="loves")
        public ArrayList<PerformancePost> mPerformancePosts = new ArrayList();

        public static PerformanceLovesResponse a(NetworkResponse networkResponse) {
            return PerformanceLovesResponse.a(networkResponse, PerformanceLovesResponse.class);
        }

        public String toString() {
            return "PerformanceLovesResponse [mResponse=" + (Object)((Object)this.a) + ", mPerformanceKey=" + this.mPerformanceKey + ", mPerformancePosts=" + this.mPerformancePosts + "]";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class PerformancePostsResponse
    extends ParsedResponse {
        @JsonProperty(value="next")
        public Integer mNext;
        @JsonProperty(value="performanceKey")
        public String mPerformanceKey;
        @JsonProperty(value="comments")
        public ArrayList<PerformancePost> mPerformancePosts = new ArrayList();

        public static PerformancePostsResponse a(NetworkResponse networkResponse) {
            return PerformancePostsResponse.a(networkResponse, PerformancePostsResponse.class);
        }

        public String toString() {
            return "PerformancePostsResponse [mResponse=" + (Object)((Object)this.a) + ", mPerformanceKey=" + this.mPerformanceKey + ", next=" + this.mNext + ", mPerformancePosts=" + this.mPerformancePosts + "]";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class PerformanceResourceInfo
    implements Parcelable {
        public static final Parcelable.Creator<PerformanceResourceInfo> CREATOR = new Parcelable.Creator<PerformanceResourceInfo>(){

            public PerformanceResourceInfo a(Parcel parcel) {
                return new PerformanceResourceInfo(parcel);
            }

            public PerformanceResourceInfo[] a(int n) {
                return new PerformanceResourceInfo[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
        @JsonProperty(value="hostname")
        public String mHostname;
        @JsonProperty(value="pop")
        public String mPOP;
        @JsonProperty(value="resourceId")
        public Long mResourceId;
        @JsonProperty(value="resourceType")
        public PerformanceResourceInfo mResourceType;

        public PerformanceResourceInfo() {
        }

        private PerformanceResourceInfo(Parcel parcel) {
            this.mPOP = parcel.readString();
            this.mResourceType = PerformanceResourceInfo.valueOf(parcel.readString());
            this.mResourceId = parcel.readLong();
            this.mHostname = parcel.readString();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int n) {
            parcel.writeString(this.mPOP);
            parcel.writeString(this.mResourceType.toString());
            parcel.writeLong(this.mResourceId.longValue());
            parcel.writeString(this.mHostname);
        }

    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class PerformanceResponse
    extends ParsedResponse {
        @JsonProperty(value="performance")
        public PerformanceV2 mPerformance;
        @JsonProperty(value="restricted")
        public boolean mRestricted;

        static PerformanceResponse a(NetworkResponse networkResponse) {
            return PerformanceResponse.a(networkResponse, PerformanceResponse.class);
        }

        public void b() {
            this.a = NetworkResponse.a();
        }

        public String toString() {
            return "PerformanceResponse [mResponse=" + (Object)((Object)this.a) + ", mPerformance=" + this.mPerformance + ", mRestricted=" + this.mRestricted + "]";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class PerformancesByPerformerResponse
    extends ParsedResponse {
        @JsonProperty(value="next")
        public Integer mNext;
        @JsonProperty(value="performanceIcons")
        public ArrayList<PerformanceV2> mPerformances;
        @JsonProperty(value="storageLimit")
        public Integer mStorageLimit;
        @JsonProperty(value="totalPerformances")
        public Integer mTotalPerformances;

        static PerformancesByPerformerResponse a(NetworkResponse networkResponse) {
            return PerformancesByPerformerResponse.a(networkResponse, PerformancesByPerformerResponse.class);
        }

        public String toString() {
            return "PerformancesByPerformerResponse{mPerformances=" + this.mPerformances + ", mTotalPerformances=" + this.mTotalPerformances + ", mStorageLimit=" + this.mStorageLimit + ", next=" + this.mNext + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class PerformancesResponse
    extends ParsedResponse {
        @JsonProperty(value="next")
        public Integer mNext;
        @JsonProperty(value="performanceIcons")
        public ArrayList<PerformanceV2> mPerformances;

        static PerformancesResponse a(NetworkResponse networkResponse) {
            return PerformancesResponse.a(networkResponse, PerformancesResponse.class);
        }

        public String toString() {
            return "PerformancesResponse [mResponse=" + (Object)((Object)this.a) + ", next=" + this.mNext + ", mPerformances=" + this.mPerformances + "]";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class PreuploadResponse
    extends ParsedResponse {
        @JsonProperty(value="resources")
        public ArrayList<PerformanceResourceInfo> mResources;

        static PreuploadResponse a(NetworkResponse networkResponse) {
            return PreuploadResponse.a(networkResponse, PreuploadResponse.class);
        }
    }

}

