package com.github.akshayavenkatesh8;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.report.ProcessingReport;
import java.io.IOException;
public class JsonValidator{
/**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws com.github.fge.jsonschema.exceptions.ProcessingException
     */
    public static void main(String[] args) throws IOException, ProcessingException {
    
            File json = new File("address.json");
            File schema = new File("schema.json");
            JsonValidator jv = new JsonValidator();
            boolean result = jv.validate(json,schema);
            System.out.println(result);
            }
    public boolean validate(File json,File schema) throws IOException, ProcessingException
{ 
    ProcessingReport report = null;
        boolean result = false;
        try{
            JsonNode schemaNode = JsonLoader.fromFile(schema);
            JsonNode dataNode = JsonLoader.fromFile(json);
            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema sch = factory.getJsonSchema(schemaNode);
            report = sch.validate(dataNode);
        }
        catch (IOException | ProcessingException e)
        {
            System.out.println("Message : "+e.getMessage());
        }
        if(report != null)
        {
            result = report.isSuccess();
        }
    
    return result;
}
}
