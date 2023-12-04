package games.highping.server.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FsatDFSUtils {

    private static Logger logger = LoggerFactory.getLogger(FsatDFSUtils.class);
    // 初始化FastDFS Client
    static {
        try {
            String filerPath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
        } catch (IOException e) {
            logger.error("初始化FastDFS失败", e.getMessage());
        }
    }

    private static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        return trackerServer;
    }

    private static StorageClient getStorageClient() throws IOException {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    public static String getTrackerUrl() {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            trackerServer = trackerClient.getTrackerServer();
            storageServer = trackerClient.getStoreStorage(trackerServer);
        } catch (IOException | MyException e) {
            logger.error("获取TrackerServer失败", e.getMessage());
        }
        return "http://" + storageServer.getInetSocketAddress().getHostString() + ":8888/";
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public String[] upload(MultipartFile file) {
        String name = file.getOriginalFilename();
        logger.info("文件名：{}", name);
        StorageClient storageClient = null;
        String[] uploadResults = null;
        try {
            // 获取StorageClient客户端
            storageClient = getStorageClient();
            uploadResults = storageClient.upload_file(file.getBytes(), name.substring(name.lastIndexOf(".") + 1), null);
        } catch (Exception e) {
            logger.error("上传文件失败", e.getMessage());
        }
        if (null == uploadResults) {
            logger.error("上传文件失败", storageClient.getErrorCode());
        }
        return uploadResults;
    }

    /**
     * 获取文件信息
     *
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static FileInfo getFileInfo(String groupName, String remoteFileName) {
        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (Exception e) {
            logger.error("获取文件信息失败", e.getMessage());
        }
        return null;
    }

    /**
     * 下载文件
     *
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static InputStream downloadFile(String groupName, String remoteFileName) {
        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            byte[] bytes = storageClient.download_file(groupName, remoteFileName);
            InputStream inputStream = new ByteArrayInputStream(bytes);
            return inputStream;
        } catch (Exception e) {
            logger.error("下载文件失败", e.getMessage());
        }
        return null;
    }

    /**
     * 删除文件
     *
     * @param groupName
     * @param remoteFileName
     */
    public static void deleteFile(String groupName, String remoteFileName) {
        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            storageClient.delete_file(groupName, remoteFileName);
        } catch (Exception e) {
            logger.error("删除文件失败", e.getMessage());
        }
    }

}
