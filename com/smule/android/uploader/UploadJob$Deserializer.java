package com.smule.android.uploader;

import com.facebook.share.internal.ShareConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smule.android.network.managers.PerformanceManager$PerformanceResourceInfo$ResourceType;
import com.smule.android.network.managers.PerformanceManager.PerformanceResourceInfo;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.uploader.UploadJob.Chunk;
import com.smule.android.uploader.UploadJob.UploadResourceInfo;
import com.smule.android.utils.JsonUtils;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class UploadJob$Deserializer extends JsonDeserializer<UploadJob> {

    class C36641 extends TypeReference<TreeSet<Chunk>> {
        final /* synthetic */ UploadJob$Deserializer f17753a;

        C36641(UploadJob$Deserializer uploadJob$Deserializer) {
            this.f17753a = uploadJob$Deserializer;
        }
    }

    public /* synthetic */ Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return m18943a(jsonParser, deserializationContext);
    }

    public UploadJob m18943a(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = (JsonNode) jsonParser.getCodec().readTree(jsonParser);
        int a = JsonUtils.m18982a(jsonNode, "version", 1);
        UploadJob uploadJob = new UploadJob();
        uploadJob.id = JsonUtils.m18992b(jsonNode, "id", null);
        uploadJob.performanceKey = JsonUtils.m18992b(jsonNode, "performanceKey", null);
        uploadJob.songUid = JsonUtils.m18992b(jsonNode, "songUid", null);
        uploadJob.arrangementKey = JsonUtils.m18992b(jsonNode, "arrangementKey", null);
        uploadJob.isOnboarding = JsonUtils.m18991a(jsonNode, "isOnboarding", false);
        uploadJob.isJoin = JsonUtils.m18991a(jsonNode, "isJoin", false);
        uploadJob.audioEffectVIPOnly = JsonUtils.m18991a(jsonNode, "audioEffectVIPOnly", false);
        uploadJob.audioEffectName = JsonUtils.m18992b(jsonNode, "audioEffectName", null);
        uploadJob.videoEffectVIPOnly = JsonUtils.m18991a(jsonNode, "videoEffectVIPOnly", false);
        uploadJob.videoEffectName = JsonUtils.m18992b(jsonNode, "videoEffectName", null);
        uploadJob.usedHeadphone = JsonUtils.m18991a(jsonNode, "usedHeadphone", false);
        uploadJob.headphonesHadMic = JsonUtils.m18991a(jsonNode, "headphonesHadMic", false);
        uploadJob.isNew = JsonUtils.m18991a(jsonNode, "isNew", false);
        uploadJob.invalidMedia = JsonUtils.m18991a(jsonNode, "invalidMedia", false);
        uploadJob.audioAnalyticsFired = JsonUtils.m18991a(jsonNode, "audioAnalyticsFired", false);
        uploadJob.isAirbrushOn = JsonUtils.m18991a(jsonNode, "isAirbrushOn", false);
        ObjectMapper a2 = JsonUtils.m18984a();
        uploadJob.performance = (PerformanceV2) a2.reader(PerformanceV2.class).readValue(jsonNode.get("performance"));
        uploadJob.uploadedChunks = (SortedSet) a2.reader(new C36641(this)).readValue(jsonNode.get("uploadedChunks"));
        if (a < 2) {
            uploadJob.uploadKey = JsonUtils.m18992b(jsonNode, "uploadKey", null);
            String b = JsonUtils.m18992b(jsonNode, "filename", null);
            PerformanceResourceInfo performanceResourceInfo = new PerformanceResourceInfo();
            performanceResourceInfo.mPOP = "";
            performanceResourceInfo.mHostname = "";
            performanceResourceInfo.mResourceId = Long.valueOf(0);
            String a3 = JsonUtils.m18986a(jsonNode, "mediaType", "");
            boolean z = true;
            switch (a3.hashCode()) {
                case -235241807:
                    if (a3.equals("AUDIO_CONTIGUOUS")) {
                        z = true;
                        break;
                    }
                    break;
                case 81665115:
                    if (a3.equals(ShareConstants.VIDEO_URL)) {
                        z = false;
                        break;
                    }
                    break;
                case 1422412342:
                    if (a3.equals("AUDIO_WITH_CHUNKS")) {
                        z = true;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    performanceResourceInfo.mResourceType = PerformanceManager$PerformanceResourceInfo$ResourceType.f16863b;
                    break;
                case true:
                case true:
                    performanceResourceInfo.mResourceType = PerformanceManager$PerformanceResourceInfo$ResourceType.AUDIO;
                    break;
                default:
                    throw new JsonParseException("can't determine resource type for file: " + b, jsonParser.getCurrentLocation());
            }
            UploadResourceInfo uploadResourceInfo = new UploadResourceInfo();
            uploadResourceInfo.mPerformanceResourceInfo = performanceResourceInfo;
            uploadResourceInfo.mResourceFilename = b;
            uploadResourceInfo.mSliceSize = JsonUtils.m18983a(jsonNode, "sliceSize", 0);
            uploadResourceInfo.mTimeoutSec = JsonUtils.m18982a(jsonNode, "timeoutSec", 0);
            uploadJob.uploadResourceInfo = uploadResourceInfo;
        } else {
            uploadJob.trackKey = JsonUtils.m18992b(jsonNode, "trackKey", null);
            uploadJob.uploadResourceInfo = (UploadResourceInfo) a2.reader(UploadResourceInfo.class).readValue(jsonNode.get("resourceInfo"));
            uploadJob.uploadKey = JsonUtils.m18992b(jsonNode, "uploadKey", null);
        }
        return uploadJob;
    }
}
