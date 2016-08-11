package org.a1exworks;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SkinService {
    private static final Logger logger = LogManager.getLogger();
    private final String        root;

    public SkinService(String root) {
        File[] dirs = { new File(root + "/skin"), new File(root + "/cape") };
        for (File dir : dirs) {
            if (!dir.exists()) dir.mkdirs();
        }
        this.root = root;
    }

    public boolean hasResource(String path) {
        if (path.startsWith("skin")||path.startsWith("cape")) {
            File file = new File(root + path);
            logger.info(file.getAbsolutePath() + ":" + file.exists());
            return file.exists();
        } else return false;
    }
}
