package manager;

import file.File;
import id.Id;

public interface FileManager {
    File getFile(Id fileId);
    File newFile(Id fileID);
}
