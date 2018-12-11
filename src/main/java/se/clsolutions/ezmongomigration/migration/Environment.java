package se.clsolutions.ezmongomigration.migration;


public class Environment {

   public enum Type {PRODUCTION, DEV, TEST, LOCAL}

   static Type environmentType;

   public static Type getEnvironment() {
      return environmentType;
   }

   public static void setEnvironment(String environment) {
      environmentType = Type.valueOf(environment.toUpperCase());
   }

   public static boolean isLocal() {
      return Type.LOCAL.equals(environmentType);
   }

   public static boolean isProduction() {
      return Type.PRODUCTION.equals(environmentType);
   }

   public static boolean isTest() {
      return Type.TEST.equals(environmentType);
   }

   public static boolean isStage() {
      return Type.DEV.equals(environmentType);
   }
}
