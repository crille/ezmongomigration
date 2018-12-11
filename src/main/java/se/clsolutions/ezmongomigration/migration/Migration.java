package se.clsolutions.ezmongomigration.migration;


import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Migration {

   private String migrationId;
   private String description;
   private LocalDateTime startTime;
   private LocalDateTime endTime;
   private String message;
   private String environment;
   private boolean runOnce;
   private boolean success;

   public String getMigrationId() {
      return migrationId;
   }

   public void setMigrationId(String migrationId) {
      this.migrationId = migrationId;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public LocalDateTime getStartTime() {
      return startTime;
   }

   public void setStartTime(LocalDateTime startTime) {
      this.startTime = startTime;
   }

   public LocalDateTime getEndTime() {
      return endTime;
   }

   public void setEndTime(LocalDateTime endTime) {
      this.endTime = endTime;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String getEnvironment() {
      return environment;
   }

   public void setEnvironment(String environment) {
      this.environment = environment;
   }

   public boolean isRunOnce() {
      return runOnce;
   }

   public void setRunOnce(boolean runOnce) {
      this.runOnce = runOnce;
   }

   public boolean isSuccess() {
      return success;
   }

   public void setSuccess(boolean success) {
      this.success = success;
   }
}
