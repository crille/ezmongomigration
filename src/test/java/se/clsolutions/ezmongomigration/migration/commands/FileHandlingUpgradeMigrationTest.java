//package se.clsolutions.ezmongomigration.migration.commands;
//
//import org.junit.runner.RunWith;
//import org.mockito.junit.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class FileHandlingUpgradeMigrationTest {
//
//   @Mock
//   GridFSDBFile file = new GridFSDBFile();
//
//   @Mock
//   GridFSDBFile existingFile = new GridFSDBFile();
//   private FileHandlingUpgradeMigration fileHandlingUpgradeMigration;
//
//   @Before
//   public void beforeMethod() {
//      fileHandlingUpgradeMigration = new FileHandlingUpgradeMigration();
//      MockitoAnnotations.initMocks(fileHandlingUpgradeMigration);
//   }
//
//
//   @Test
//   public void shouldReplaceFileVersionWhenGreaterThanPrevious() {
//
//      String fileName = "/Reforce International pitchcard.pdf";
//
//      Map<String, GridFSDBFile> resultFiles = new HashMap<String, GridFSDBFile>();
//
//      DBObject metadataExistingFile = new BasicDBObject();
//      metadataExistingFile.put("version", "1.0");
//      when(existingFile.getFilename()).thenReturn(fileName);
//      when(existingFile.getMetaData()).thenReturn(metadataExistingFile);
//      resultFiles.put(existingFile.getFilename(), existingFile);
//
//      DBObject metadata = new BasicDBObject();
//      metadata.put("version", "2.0");
//      when(file.getFilename()).thenReturn(fileName);
//      when(file.getMetaData()).thenReturn(metadata);
//
//      fileHandlingUpgradeMigration.filterLatestFileVersion(resultFiles, file, existingFile);
//
//      assertEquals(1, resultFiles.size());
//      assertEquals(file, resultFiles.get(fileName));
//   }
//
//
//   @Test
//   public void shouldNotReplaceExistingFileVersionWhenVersionIsLessThanPrevious() {
//
//      String fileName = "/Reforce International pitchcard.pdf";
//
//      Map<String, GridFSDBFile> resultFiles = new HashMap<String, GridFSDBFile>();
//
//      DBObject metadataExistingFile = new BasicDBObject();
//      metadataExistingFile.put("version", "2.0");
//      when(existingFile.getFilename()).thenReturn(fileName);
//      when(existingFile.getMetaData()).thenReturn(metadataExistingFile);
//      resultFiles.put(existingFile.getFilename(), existingFile);
//
//      DBObject metadata = new BasicDBObject();
//      metadata.put("version", "1.0");
//      when(file.getFilename()).thenReturn(fileName);
//      when(file.getMetaData()).thenReturn(metadata);
//
//      fileHandlingUpgradeMigration.filterLatestFileVersion(resultFiles, file, existingFile);
//
//      assertEquals(1, resultFiles.size());
//      assertEquals(existingFile, resultFiles.get(fileName));
//   }
//
//   @Test
//   public void shouldRemoveDateFromFilePath() {
//      //Setup
//      String filePath = "/docs/reforce/process-e65eb25b-1bbd-473b-8e2f-e533f941e905/160413-1149 900 meter i Sverige AB.pdf";
//
//      //Action
//      String result = fileHandlingUpgradeMigration.removeDateInFilePath(filePath, TenantId.id("reforce"));
//
//      //Verify
//      assertNotNull(result);
//      assertEquals("/docs/reforce/process-e65eb25b-1bbd-473b-8e2f-e533f941e905/900 meter i Sverige AB.pdf", result);
//   }
//}