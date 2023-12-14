package com.nashss.se.collectorsstash.lambda;

import com.nashss.se.collectorsstash.activity.request.GetAllComicBooksRequest;
import com.nashss.se.collectorsstash.activity.results.GetAllComicBooksResults;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetAllComicBooksLambda
        extends LambdaActivityRunner<GetAllComicBooksRequest, GetAllComicBooksResults>
        implements RequestHandler<LambdaRequest<GetAllComicBooksRequest>, LambdaResponse> {


    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetAllComicBooksRequest> input, Context context) {
        return super.runActivity(() -> input.fromPath(path ->
                GetAllComicBooksRequest.builder()
                        .withSeriesId(path.get("seriesId"))
                        .build()), (request, serviceComponent) ->
                serviceComponent.provideGetAllComicBooksActivity().handleRequest(request)
        );
    }
}
