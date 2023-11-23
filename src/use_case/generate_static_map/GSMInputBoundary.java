package use_case.generate_static_map;

import java.io.IOException;

/**
 * Input boundary for the generate static map use case.
 */
public interface GSMInputBoundary {
    void execute(GSMInputData inputData) throws IOException;
}
