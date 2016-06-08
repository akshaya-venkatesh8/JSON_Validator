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
    
            String json = "{\"address\":{\"name\":\"Lily Joanna\",\"streetnumber\":\"123\",\"streetname\":\"Main Street\",\"secondary address\":\"BLDG 12\",\"city\":\"San Francisco\",\"statecode\":\"CA\",\"zipcode\":\"94111-1234\"}}";
            String schema = "{\"$schema\": \"http://json-schema.org/draft-04/schema#\",\"address\":{\"type\":\"object\",\"properties\":{\"streetnumber\":{\"type\":\"integer\"},\"streetname\":{\"type\":\"string\",\"minLength\":1,\"maxLength\":34},\"secondaryaddress\":{\"type\":\"string\",\"pattern\": \"APT|BSMT|DEPT|FL|FRNT|HNGR|KEY|LBBY|LOT|LOWR|OFC|PH|PIER|REAR|RM|SIDE|SLIP|SPC|STOP|STE|TRLR|UNIT|UPPR|BLDG|[A-Z]{3} [0-9]{2}([0-9])?\"},\"city\":{\"type\":\"string\",\"minLength\":1,\"maxLength\":24},\"statecode\":{\"type\":\"string\",\"pattern\":\"[A-Z]{2}\"},\"zipcode\":{\"type\":\"string\",\"pattern\":\"[0-9]{5}(-[0-9]{4})?\"}},\"required\":[\"streetnumber\",\"streetname\",\"city\",\"statecode\",\"zipcode\"]}}";
            JsonValidator jv = new JsonValidator();
            boolean result = jv.validate(json,schema);
            System.out.println(result);
            }
    public boolean validate(String json,String schema) throws IOException, ProcessingException
{ 
    ProcessingReport report = null;
        boolean result = false;
        try{
            JsonNode schemaNode = JsonLoader.fromString(schema);
            JsonNode dataNode = JsonLoader.fromString(json);
            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema sch = factory.getJsonSchema(schemaNode);
            report = sch.validate(dataNode);
        }
        catch (IOException | ProcessingException e)
        {
            System.out.println("ERROR : "+e.getMessage());
        }
        if(report != null)
        {
            result = report.isSuccess();
        }
    
    return result;
}
}
