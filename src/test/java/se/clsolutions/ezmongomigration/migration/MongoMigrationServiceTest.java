//package se.clsolutions.ezmongomigration.migration;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(MockitoJUnitRunner.class)
//public class MongoMigrationServiceTest {
//
//   @Mock
//   MigrationRepository migrationRepository;
//
//   @InjectMocks
//   MigrationService mongoMigrationService;
//
//   @Before
//   public void beforeMethod() {
//      mongoMigrationService.setEnvironment(Environment.Type.LOCAL.name());
//      Environment.setEnvironment(Environment.Type.LOCAL.name());
//   }
//
//   @Test
//   public void shouldNotRunMigrationsWhenAlreadyExecuted() {
//
//      List<MigrationCommand> migrationCommands = new ArrayList<>();
////      migrationCommands.add(new DeletePitchCardsHavingMissingProspect());
//
//      mongoMigrationService.setMigrations(migrationCommands);
//
//      //Setup
////      Migration migration = new Migration();
////      migration.setMigrationId("migration");
////      Migration migration1 = new Migration();
////      migration1.setMigrationId(DeletePitchCardsHavingMissingProspect.class.getSimpleName());
////      migration1.setSuccess(true);
////      Migration objectiveNameToProcessMigration = new Migration();
////      objectiveNameToProcessMigration.setMigrationId(ObjectiveToProcessMigration.MIGRATION_ID);
////
////      when(migrationRepository.findAll()).thenReturn(Lists.newArrayList(migration, migration1, objectiveNameToProcessMigration));
////
////      //Action
////      mongoMigrationService.executeAllMigrations();
////
////      //Verify
////      verify(migrationRepository, never()).save(any(Migration.class));
//   }
//
//   @Test
//   public void shouldRunMigrationWhenNotAlreadyExecuted() {
//
////      // SETUP
////      List migrations = Lists.newArrayList(
////            migrateLatestActivityOnProspect,
////            new ObjectiveToProcessMigration(maintenanceService));
////      when(tenantService.findByTenantId(TenantId.id("reforce"))).thenReturn(new Tenant(TenantId.id("reforce"), "Reforce"));
////      when(prospectService.findByTenantId(TenantId.id("reforce"))).thenReturn(Collections.emptyList());
////      mongoMigrationService.setMigrations(migrations);
////
////      //Action
////      mongoMigrationService.executeAllMigrations();
////
////      //Verify
////      verify(migrationRepository, times(2)).save(any(Migration.class));
//   }
//
//}