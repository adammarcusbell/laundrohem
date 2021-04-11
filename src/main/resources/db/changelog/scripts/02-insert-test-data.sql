INSERT INTO houseblock (id, uuid, name) values (1, '7264e8ea-2356-4e50-a2b6-0d462b467520', 'Block One');

INSERT INTO household (uuid, name, household_block_id) values ('6bf96bf6-6852-4451-811f-0dbbaab1e644', 'A.O & F.E Bergman', 1);
INSERT INTO household (uuid, name, household_block_id) values ('a1f3bcc7-6e29-4645-ada5-3c2c6d0d4fbd', 'J.P Smith', 1);
INSERT INTO household (uuid, name, household_block_id) values ('0314bd86-3e80-4114-8fb2-a0fa035e73d1', 'K & R Mattson', 1);
INSERT INTO household (uuid, name, household_block_id) values ('c23a5a69-67f4-4441-b5e3-8e44cd58c735', 'S. Graff', 1);
INSERT INTO household (uuid, name, household_block_id) values ('ac969a48-4344-4bc2-b01a-ab4df37b86f0', 'P.A & F. Dahl', 1);
INSERT INTO household (uuid, name, household_block_id) values ('4575b5f2-e671-477e-98f8-0000f7bc38bf', 'K. Martin', 1);
INSERT INTO household (uuid, name, household_block_id) values ('44b75964-0be0-4432-af07-28bb73c234bc', 'F. & K. Bueller', 1);
INSERT INTO household (uuid, name, household_block_id) values ('311af551-87d7-480d-9107-667d963af366', 'P.T Herring', 1);
INSERT INTO household (uuid, name, household_block_id) values ('b859022d-b2a4-4d3a-be5e-f647ec3a5e74', 'E.U Dunder', 1);
INSERT INTO household (uuid, name, household_block_id) values ('7db33fb8-ed81-4cdd-9921-91425a578a15', 'T. & F.G Pink', 1);
INSERT INTO household (uuid, name, household_block_id) values ('bb9b7c46-5e82-4b10-abb1-86130e601ddd', 'S. & R.I Lawkins', 1);
INSERT INTO household (uuid, name, household_block_id) values ('cf99fd20-510a-4b3d-9b8d-1a66de46c456', 'G.E Hunter', 1);
INSERT INTO household (uuid, name, household_block_id) values ('6eb5aa53-412e-4273-b1f1-8e5872b9056b', 'H.R Grimm', 1);
INSERT INTO household (uuid, name, household_block_id) values ('9d123585-c14b-43e6-a31d-5a7570a7b58f', 'R.E & S.C Jackson', 1);
INSERT INTO household (uuid, name, household_block_id) values ('2e7cb343-3d86-46e4-ac47-4d13c7a58d52', 'J. Dryden', 1);
INSERT INTO household (uuid, name, household_block_id) values ('10d1fbfd-6fde-4ebe-a5f3-7e1f9244849f', 'D.E & F. Cruz', 1);
INSERT INTO household (uuid, name, household_block_id) values ('1c37b88b-1c90-4380-8f90-2ca97c3ad59f', 'W.L Blake', 1);
INSERT INTO household (uuid, name, household_block_id) values ('7894d4d0-5d64-4ef3-8d11-a8a0f803cc45', 'G. Harrison', 1);
INSERT INTO household (uuid, name, household_block_id) values ('49d7d6fc-9259-4bae-9008-7b4d0f222f3f', 'D.E & W.S McLeash', 1);
INSERT INTO household (uuid, name, household_block_id) values ('9656c3c5-4be1-4ab1-a435-a4623e0d79d7', 'F.G Butterworth', 1);

INSERT INTO service_feature (uuid, type, duration) values('5d1828d0-d588-4bca-ae10-b7ae3353bb12', 'QUICK_RINSE', 20);
INSERT INTO service_feature (uuid, type, duration) values('af6491f9-9d12-4f20-83e5-406d33a7bda5', 'ECO_WASH', 120);
INSERT INTO service_feature (uuid, type, duration) values('ce0bf0c7-728c-4132-8ec3-b6f972a52eb7', 'NORMAL_WASH', 60);
INSERT INTO service_feature (uuid, type, duration) values('3d93c18e-42d2-4f71-a480-8d8a9909febd', 'SPIN_DRY', 120);

INSERT INTO appliance (uuid, name) values ('b1c58fe3-6e9a-408d-a00b-1cb6e80f0467', 'Front Load Perfect Steam™ Washer with LuxCare® Wash and SmartBoost®');
INSERT INTO appliance (uuid, name) values ('035809cc-5969-4ca3-8430-cf1d4c180e96', 'Front Load Perfect Steam™ Gas Dryer with PredictiveDry™ and Instant Refresh');
INSERT INTO appliance (uuid, name) values ('3d9fbddf-2999-4bc1-b2de-d910593fbf50', 'Front Load Perfect Steam™ Washer with LuxCare® Wash and SmartBoost®');
INSERT INTO appliance (uuid, name) values ('43be5272-565c-4917-954b-2a855003a513', 'Front Load Perfect Steam™ Gas Dryer with PredictiveDry™ and Instant Refresh');

-- Assign the Service Features to the Appliances --
INSERT INTO appliance_service_feature (service_feature_id, appliance_id)
select sf.id, ap.id from service_feature sf, appliance ap where sf.uuid = '5d1828d0-d588-4bca-ae10-b7ae3353bb12' and ap.uuid = 'b1c58fe3-6e9a-408d-a00b-1cb6e80f0467';
INSERT INTO appliance_service_feature (service_feature_id, appliance_id)
select sf.id, ap.id from service_feature sf, appliance ap where sf.uuid = 'af6491f9-9d12-4f20-83e5-406d33a7bda5' and ap.uuid = 'b1c58fe3-6e9a-408d-a00b-1cb6e80f0467';
INSERT INTO appliance_service_feature (service_feature_id, appliance_id)
select sf.id, ap.id from service_feature sf, appliance ap where sf.uuid = 'ce0bf0c7-728c-4132-8ec3-b6f972a52eb7' and ap.uuid = 'b1c58fe3-6e9a-408d-a00b-1cb6e80f0467';
INSERT INTO appliance_service_feature (service_feature_id, appliance_id)
select sf.id, ap.id from service_feature sf, appliance ap where sf.uuid = '3d93c18e-42d2-4f71-a480-8d8a9909febd' and ap.uuid = '035809cc-5969-4ca3-8430-cf1d4c180e96';
INSERT INTO appliance_service_feature (service_feature_id, appliance_id)
select sf.id, ap.id from service_feature sf, appliance ap where sf.uuid = '5d1828d0-d588-4bca-ae10-b7ae3353bb12' and ap.uuid = '3d9fbddf-2999-4bc1-b2de-d910593fbf50';
INSERT INTO appliance_service_feature (service_feature_id, appliance_id)
select sf.id, ap.id from service_feature sf, appliance ap where sf.uuid = 'af6491f9-9d12-4f20-83e5-406d33a7bda5' and ap.uuid = '3d9fbddf-2999-4bc1-b2de-d910593fbf50';
INSERT INTO appliance_service_feature (service_feature_id, appliance_id)
select sf.id, ap.id from service_feature sf, appliance ap where sf.uuid = 'ce0bf0c7-728c-4132-8ec3-b6f972a52eb7' and ap.uuid = '3d9fbddf-2999-4bc1-b2de-d910593fbf50';
INSERT INTO appliance_service_feature (service_feature_id, appliance_id)
select sf.id, ap.id from service_feature sf, appliance ap where sf.uuid = '3d93c18e-42d2-4f71-a480-8d8a9909febd' and ap.uuid = '43be5272-565c-4917-954b-2a855003a513';

-- Create the Laundry Room --
INSERT INTO laundryroom (uuid, name, household_block_id) values ('05442846-58c1-4cda-8941-29f90fe23625', 'Laundry Room 1', 1);
INSERT INTO laundryroom (uuid, name, household_block_id) values ('b47a22ba-336e-43a4-81ab-d998dfba92ce', 'Laundry Room 2', 1);

-- Assign the Appliances to the Laundry Rooms -
INSERT INTO laundryroom_appliances (laundry_room_id, appliance_id)
select lr.id, ap.id from laundryroom lr, appliance ap where lr.uuid = '05442846-58c1-4cda-8941-29f90fe23625' and ap.uuid = 'b1c58fe3-6e9a-408d-a00b-1cb6e80f0467';
INSERT INTO laundryroom_appliances (laundry_room_id, appliance_id)
select lr.id, ap.id from laundryroom lr, appliance ap where lr.uuid = '05442846-58c1-4cda-8941-29f90fe23625' and ap.uuid = '035809cc-5969-4ca3-8430-cf1d4c180e96';
INSERT INTO laundryroom_appliances (laundry_room_id, appliance_id)
select lr.id, ap.id from laundryroom lr, appliance ap where lr.uuid = 'b47a22ba-336e-43a4-81ab-d998dfba92ce' and ap.uuid = '3d9fbddf-2999-4bc1-b2de-d910593fbf50';
INSERT INTO laundryroom_appliances (laundry_room_id, appliance_id)
select lr.id, ap.id from laundryroom lr, appliance ap where lr.uuid = 'b47a22ba-336e-43a4-81ab-d998dfba92ce' and ap.uuid = '43be5272-565c-4917-954b-2a855003a513';