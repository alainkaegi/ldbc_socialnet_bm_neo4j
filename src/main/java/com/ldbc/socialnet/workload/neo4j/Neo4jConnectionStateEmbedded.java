package com.ldbc.socialnet.workload.neo4j;

import org.neo4j.cypher.internal.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;

import com.ldbc.driver.DbConnectionState;

public class Neo4jConnectionStateEmbedded extends DbConnectionState
{
    private final GraphDatabaseService db;
    private final ExecutionEngine executionEngine;

    public Neo4jConnectionStateEmbedded( GraphDatabaseService db, ExecutionEngine executionEngine )
    {
        super();
        this.db = db;
        this.executionEngine = executionEngine;
    }

    public GraphDatabaseService db()
    {
        return db;
    }

    public ExecutionEngine executionEngine()
    {
        return executionEngine;
    }
}
