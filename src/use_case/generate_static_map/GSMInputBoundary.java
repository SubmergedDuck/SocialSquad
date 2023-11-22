package use_case.generate_static_map;

import java.io.IOException;

public interface GSMInputBoundary {
    void execute(GSMInputData inputData) throws IOException;
}
