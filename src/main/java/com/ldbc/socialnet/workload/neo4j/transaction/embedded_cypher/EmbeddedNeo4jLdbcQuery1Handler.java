package com.ldbc.socialnet.workload.neo4j.transaction.embedded_cypher;

import java.util.List;

import org.apache.log4j.Logger;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;

import com.ldbc.driver.DbException;
import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.socialnet.workload.LdbcQuery1;
import com.ldbc.socialnet.workload.LdbcQuery1Result;
import com.ldbc.socialnet.workload.neo4j.Neo4jConnectionStateEmbedded;
import com.ldbc.socialnet.workload.neo4j.transaction.Neo4jQuery1;
import com.ldbc.socialnet.workload.neo4j.utils.Utils;

public class EmbeddedNeo4jLdbcQuery1Handler extends OperationHandler<LdbcQuery1>
{
    private final static Logger logger = Logger.getLogger( EmbeddedNeo4jLdbcQuery1Handler.class );

    @Override
    protected OperationResult executeOperation( LdbcQuery1 operation ) throws DbException
    {
        ExecutionEngine engine = ( (Neo4jConnectionStateEmbedded) dbConnectionState() ).executionEngine();
        GraphDatabaseService db = ( (Neo4jConnectionStateEmbedded) dbConnectionState() ).db();
        Neo4jQuery1 query1 = new Neo4jQuery1EmbeddedCypher();
        List<LdbcQuery1Result> result = null;
        // TODO find way to do this
        int resultCode = 0;
        try
        {
            result = Utils.iteratorToList( query1.execute( db, engine, operation ) );
        }
        catch ( Exception e )
        {
            logger.error( String.format( "Error executing query\n%s", Utils.stackTraceToString( e ) ) );
            resultCode = -1;
        }

        return operation.buildResult( resultCode, result );
    }
}
