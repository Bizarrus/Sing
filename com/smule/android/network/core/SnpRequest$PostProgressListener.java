package com.smule.android.network.core;

import com.smule.android.uploader.UploadJob.Chunk;
import java.util.SortedSet;

public interface SnpRequest$PostProgressListener {
    void onChunkTransfered(SortedSet<Chunk> sortedSet);
}
