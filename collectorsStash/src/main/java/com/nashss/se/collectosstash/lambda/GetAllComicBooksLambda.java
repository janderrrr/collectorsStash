package com.nashss.se.collectosstash.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.collectosstash.activity.request.GetAllComicBooksRequest;
import com.nashss.se.collectosstash.activity.results.GetAllComicBooksResults;

public class GetAllComicBooksLambda
        extends LambdaActivityRunner<GetAllComicBooksRequest, GetAllComicBooksResults>
        implements RequestHandler<LambdaRequest<GetAllComicBooksRequest>, LambdaResponse> {


    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetAllComicBooksRequest> input, Context context) {
        return super.runActivity(() -> input.fromQuery(query ->
                GetAllComicBooksRequest.builder()
                        .withSeriesTitle(query.get("seriesTitle"))
                        .withVolumeNumber(query.get("volumeNumber"))
                        .build()), (request, serviceComponent) ->
                serviceComponent.provideGetAllComicBooksActivity().handleRequest(request)
        );
    }
}
