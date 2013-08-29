package com.ldbc.socialnet.neo4j.domain;

import com.ldbc.socialnet.load.neo4j.tempindex.TempIndex;

public class LanguagesBatchIndex implements TempIndex<Long, Long>
{
    private final TempIndex<Long, Long> tempIndex;

    public LanguagesBatchIndex( TempIndex<Long, Long> tempIndex )
    {
        this.tempIndex = tempIndex;
    }

    @Override
    public void put( Long k, Long v )
    {
        tempIndex.put( k, v );
    }

    @Override
    public Long get( Long k )
    {
        return tempIndex.get( k );
    }

    @Override
    public void shutdown()
    {
        tempIndex.shutdown();
    }
}
