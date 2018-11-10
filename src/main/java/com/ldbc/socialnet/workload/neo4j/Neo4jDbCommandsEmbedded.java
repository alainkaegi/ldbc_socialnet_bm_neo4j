package com.ldbc.socialnet.workload.neo4j;

import java.io.File;

import org.neo4j.cypher.internal.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import com.ldbc.driver.Db;
import com.ldbc.driver.DbConnectionState;
import com.ldbc.driver.DbException;
import com.ldbc.socialnet.workload.LdbcQuery1;
import com.ldbc.socialnet.workload.LdbcQuery3;
import com.ldbc.socialnet.workload.LdbcQuery4;
import com.ldbc.socialnet.workload.neo4j.transaction.embedded_cypher.EmbeddedNeo4jLdbcQuery1Handler;
import com.ldbc.socialnet.workload.neo4j.transaction.embedded_cypher.EmbeddedNeo4jLdbcQuery3Handler;
import com.ldbc.socialnet.workload.neo4j.transaction.embedded_cypher.EmbeddedNeo4jLdbcQuery4Handler;
import com.ldbc.socialnet.workload.neo4j.utils.Config;

public class Neo4jDbCommandsEmbedded extends Neo4jDbCommands
{
    final private String path;
    private ExecutionEngine queryEngine;
    private GraphDatabaseService db;
    private DbConnectionState dbConnectionState;

    public Neo4jDbCommandsEmbedded( String path )
    {
        this.path = path;
    }

    @Override
    public void init()
    {
        db = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder( new File( path ) ).setConfig( Config.NEO4J_RUN_CONFIG ).newGraphDatabase();
        queryEngine = null;
        dbConnectionState = new Neo4jConnectionStateEmbedded( db, queryEngine );
        registerShutdownHook( db );
    }

    @Override
    public void cleanUp()
    {
        db.shutdown();
    }

    @Override
    public DbConnectionState getDbConnectionState()
    {
        return dbConnectionState;
    }

    @Override
    public void registerHandlersWithDb( Db db ) throws DbException
    {
        db.registerOperationHandler( LdbcQuery1.class, EmbeddedNeo4jLdbcQuery1Handler.class );
        db.registerOperationHandler( LdbcQuery3.class, EmbeddedNeo4jLdbcQuery3Handler.class );
        db.registerOperationHandler( LdbcQuery4.class, EmbeddedNeo4jLdbcQuery4Handler.class );
    }

    private static void registerShutdownHook( final GraphDatabaseService graphDb )
    {
        Runtime.getRuntime().addShutdownHook( new Thread()
        {
            @Override
            public void run()
            {
                graphDb.shutdown();
            }
        } );
    }
}
